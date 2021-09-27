package exercicis

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class FitxerImatge(fEnt: File) {
    //Declaramos el objeto con el que operaremos
    var file: File = File("")

    init {
        // Constructor
        // Control d'existència del fitxer i control de l'extensió .bmp (traure missatges d'error)
        // En cas que tot siga correcte, inicialitzar f amb fEnt
        if (fEnt.exists() && fEnt.extension == "bmp"){
            /*
            Si durante la ejecucion el fichero suministrado cumple las condiciones
            nuestro fichero apuntara al de entrada.
             */
            file = fEnt
        } else
            throw IOException("Error en la entrada de datos.")
    }


    fun transformaNegatiu() {
        /*
        Los flujos se deben instanciar en el momento de ser usados
         */
        val fileInStream = FileInputStream(file)
        val fileOutStream = FileOutputStream(file.nameWithoutExtension.plus("_n.bmp"))
        for (i in 0..53){
            fileOutStream.write(fileInStream.read())
        }
        /*
        Como no hemos cerrado el flujo de datos el bucle while
        comenzara el byte 54, el que necesitamos
         */
        var byte = fileInStream.read()
        while (byte != -1){
            /*
            Usamos la variable byte porque contiene la ultima lectura del fichero,
            de volver a usar .read leeriamos dos veces
             */
            fileOutStream.write(255-byte)
            byte = fileInStream.read()
        }
        /*
        Despues de haber sido usado cerramos los flujos de datos
         */
        fileInStream.close()
        fileOutStream.close()
        // Transformar a negatiiu i guardar en _n.bmp

    }

    fun transformaObscur() {
        val fileInStream = FileInputStream(file)
        val fileOutStream = FileOutputStream(file.nameWithoutExtension.plus("_o.bmp"))
        // Transformar a una imatge més fosca i guardar en _o.bmp
        for (i in 0..53){
            fileOutStream.write(fileInStream.read())
        }
        var byte = fileInStream.read()
        while (byte != -1){
            fileOutStream.write(byte/2)
            byte = fileInStream.read()
        }
        fileInStream.close()
        fileOutStream.close()
    }

    fun transformaBlancNegre() {
        val fileInStream = FileInputStream(file)
        val fileOutStream = FileOutputStream(file.nameWithoutExtension.plus("_bn.bmp"))
        for (i in 0..53){
            fileOutStream.write(fileInStream.read())
        }
        var byteR = fileInStream.read()
        var byteG = fileInStream.read()
        var byteB = fileInStream.read()
        var byteAverage = (byteR+byteG+byteB)/3
        while (byteR !=  -1 && byteG != -1 && byteB != -1){
            fileOutStream.write(byteAverage)
            byteR = fileInStream.read()
            byteG = fileInStream.read()
            byteB = fileInStream.read()
            byteAverage = (byteR+byteG+byteB)/3
        }
        fileInStream.close()
        fileOutStream.close()
        // Transformar a una imatge en blanc i negre i guardar en _bn.bmp
    }
}

