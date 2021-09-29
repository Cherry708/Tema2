package exercicis

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class Exercici_2_1(fEnt: File) {
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

    /**
     * Modifica los bytes de un fichero .bmp
     * para transformar la imagen en negativo.
     *
     * Por cada byte posterior al 54 de la imagen
     * original escribe en otro fichero,
     * con el nombre original y terminado en "_n.bmp",
     * 255-byte, donde byte es el byte de la imagen original.
     */
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
        Al no haber cerrado el flujo de datos el bucle while
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
    }

    /**
     * Modifica los bytes de un fichero .bmp
     * escribriendo en cada byte posterior al 54
     * la mitad del byte leido sobre un fichero con
     * el nombre original terminado en "_o.bmp"
     */
    fun transformaObscur() {
        val fileInStream = FileInputStream(file)
        val fileOutStream = FileOutputStream(file.nameWithoutExtension.plus("_o.bmp"))
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

    /**
     * Modifica una imagen a blanco y negro leyendo
     * tres bytes, obteniendo su media y escribiendo esta
     * en otro fichero con el nombre original terminado en "_bn.bmp"
     */
    fun transformaBlancNegre() {
        val fileInStream = FileInputStream(file)
        val fileOutStream = FileOutputStream(file.nameWithoutExtension.plus("_bn.bmp"))
        for (i in 0..53){
            fileOutStream.write(fileInStream.read())
        }
        /*
        Asignamos un byte a cada color
         */
        var byteR = fileInStream.read()
        var byteG = fileInStream.read()
        var byteB = fileInStream.read()
        while (byteR != -1){
            /*
            Debemos escribir tres veces, una por cada byte,
            de no hacerlo la imagen seria un tercio del tamaño original
             */
            fileOutStream.write((byteR+byteG+byteB)/3)
            fileOutStream.write((byteR+byteG+byteB)/3)
            fileOutStream.write((byteR+byteG+byteB)/3)
            byteR = fileInStream.read()
            byteG = fileInStream.read()
            byteB = fileInStream.read()
        }
        fileInStream.close()
        fileOutStream.close()
    }
}

