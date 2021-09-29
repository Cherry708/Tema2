package exercicis

import java.io.File

fun main(args: Array<String>){
    val f = File("Penyagolosa.bmp")

    val fi = Exercici_2_1(f)
    fi.transformaNegatiu()
    fi.transformaObscur()
    //voluntari
    fi.transformaBlancNegre()
}

