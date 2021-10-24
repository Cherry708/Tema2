package exercicis

import javax.swing.*
import java.awt.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.FileWriter

class Exercici_2_2_Pantalla : JFrame() {
    val et_f = JLabel("Fitxer:")
    val fitxer = JTextField(25)
    val obrir = JButton("Obrir")
    val guardar = JButton("Guardar")
    val et_a = JLabel("Contingut:")
    val area = JTextArea(10, 50)
    val scrollPane = JScrollPane(area)

    // en iniciar posem un contenidor per als elements anteriors
    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        setLayout(GridLayout(2, 1))
        setTitle("Editor de text")

        val panell1 = JPanel(GridLayout(0, 1))
        val panell1_1 = JPanel(FlowLayout())
        panell1.add(panell1_1)
        panell1_1.add(et_f)
        panell1_1.add(fitxer)

        val panell1_2 = JPanel(FlowLayout())
        panell1.add(panell1_2)
        panell1_2.add(obrir)
        panell1_2.add(guardar)
        val panell2 = JPanel(GridLayout(0, 1))
        panell2.add(scrollPane)
        area.setEditable(true)

        add(panell1)
        add(panell2)
        pack()

        obrir.addActionListener {
            /*
            Asignamos el texto del textField fitxer como ruta para file.
            Comprobamos si existe el file con esta ruta existe, la extensión también se debe incluir.
            En caso de que exista el fichero el componente textArea se ocupa con un string vacio.
            Declaramos e instanciamos el flujo de datos y, mientras haya algo que leer, lo leemos y
            añadimos al componente textArea.
             */
            val file = File(fitxer.getText())
            try {
                if (file.exists()) {
                    area.text = ""
                    val fileReader = FileReader(file)
                    var c = fileReader.read()
                    while (c != -1) {
                        area.append(c.toChar().toString())
                        c = fileReader.read()
                    }
                    fileReader.close()
                } else JOptionPane.showMessageDialog(null, "No es posible acceder al fichero especificado.")
            } catch (e: FileNotFoundException) {
            }
        }

        guardar.addActionListener {
            /*
            Si se cumple que el campo fichero tiene nombre, abriremos el flujo de datos
            y escribiremos todos los caracteres en el fichero especificado.
             */
            try {
                if (fitxer.getText() != null) {
                    val file = File(fitxer.getText())
                    val fileWriter = FileWriter(file)
                    fileWriter.write(area.text)
                    fileWriter.close()
                }
            } catch (e: FileNotFoundException) {
                JOptionPane.showMessageDialog(null, "Debes especificar el fichero de salida.")
            }
        }
    }
}

private fun crearMostrarFinestra() {

    val frame = Exercici_2_2_Pantalla()
    frame.isVisible = true
}

fun main(args: Array<String>) {
    EventQueue.invokeLater(::crearMostrarFinestra)
}

