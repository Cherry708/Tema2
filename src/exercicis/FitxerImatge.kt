package exercicis

import java.io.File
import java.io.IOException

class FitxerImatge(fEnt: File) {
    var f: File = File("")

    init {
        // Constructor
        // Control d'existència del fitxer i control de l'extensió .bmp (traure missatges d'error)
        // En cas que tot siga correcte, inicialitzar f amb fEnt
        if (fEnt.exists() && fEnt.extension == ".bmp"){
            f = fEnt
        } else
            throw IOException("Error en la entrada de datos.")


    }

    fun transformaNegatiu() {


        // Transformar a negatiiu i guardar en _n.bmp

    }

    fun transformaObscur() {
        // Transformar a una imatge més fosca i guardar en _o.bmp

    }

    /* Partvoluntària
    fun transformaBlancNegre() {
        // Transformar a una imatge en blanc i negre i guardar en _bn.bmp

    }
 */
}

