package exemples

import java.io.FileInputStream

fun main(args: Array<String>){
    val f_in = FileInputStream("f1.txt")
    var c = f_in.read()
    //println(c::class)
    //Para ser más precisos, c de character debería ser byte, ya que leemos bytes y c tiene un byte asignado
    while (c!=-1){
        println(c.toChar())
        c = f_in.read()
    }
    f_in.close()
}

/**
 * El metodo read de FileInputStream devuelve un byte en forma de int
 */