package exercicis

import javax.swing.*
import java.awt.*
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.lang.NullPointerException

class Exercici_2_3 : JFrame() {
    val area = JTextArea()
    val scrollPane = JScrollPane(area)

    val menu_p = JMenuBar()

    val menu_arxiu = JMenu("Arxiu")
    val menu_ajuda = JMenu("Ajuda")

    val obrir = JMenuItem("Obrir")
    val seleccionarFichero = JFileChooser("")
    val guardar = JMenuItem("Guardar")
    val guardarCom = JMenuItem("Guardar com ...")
    val eixir = JMenuItem("Eixir")

    val quantA = JMenuItem("Quant a Editor")

    val fCh = JFileChooser()

    // en iniciar posem un contenidor per als elements anteriors
    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        setLayout(BorderLayout())
        setTitle("Editor de text més avançat")
        add(scrollPane)
        area.setEditable(true)

        setSize(750, 400)
        setJMenuBar(menu_p)

        menu_p.add(menu_arxiu)
        menu_p.add(menu_ajuda)

        menu_arxiu.add(obrir)
        menu_arxiu.add(guardar)
        menu_arxiu.add(guardarCom)
        menu_arxiu.add(JSeparator())
        menu_arxiu.add(eixir)

        menu_ajuda.add(quantA);

        obrir.addActionListener { obrir() }

        guardar.addActionListener { guardar() }

        guardarCom.addActionListener { guardarCom() }

        eixir.addActionListener { eixir() }

        quantA.addActionListener { quantA() }
    }

    fun obrir() {
        seleccionarFichero.showOpenDialog(null)
        try {

            if (seleccionarFichero.selectedFile.isFile) {
                this.title = "Editor de text més avançat"
                val titulo = seleccionarFichero.selectedFile.name
                this.title = this.title.plus(" - $titulo")
                area.text = ""
                val reader = FileReader(seleccionarFichero.selectedFile)
                var c = reader.read()
                while (c != -1) {
                    area.append(c.toChar().toString())
                    c = reader.read()
                }
                reader.close()
            }
        } catch (e: NullPointerException){}
        // Instruccions per a obrir un fitxer i posar el contingut en el JTextArea

    }

    fun guardar() {
        try {
            if (seleccionarFichero.selectedFile.exists()) {
                val writer = FileWriter(File(seleccionarFichero.selectedFile.name))
                for (c in area.text) {
                    writer.write(c.toString())
                }
                writer.close()
            }         // Instruccions per a guardar el contingut del JTextArea al fitxer.
        } catch (e: NullPointerException) {
            guardarCom()
        }
    }

    fun guardarCom() {
        try {

            seleccionarFichero.showSaveDialog(null)
            val writer = FileWriter(File(seleccionarFichero.selectedFile.name))
            for (c in area.text) {
                writer.write(c.toString())
            }
            writer.close()
        } catch (e: NullPointerException){}

        // Instruccions per a guardar el contingut del JTextArea al fitxer, amb la possibilitat de canviar el nom

    }

    fun eixir() {
        // Instruccions per a eixir
        System.exit(0)
    }

    fun quantA() {
        val mensaje = "Editor de texto avanzado escrito en Kotlin, widgets generados mediante la biblioteca Swing."
        val autor = "Autor: Ruben Serrano"
        // Instruccions per a mostrar un diàleg amb la versió (Acerca de...)
        JOptionPane.showMessageDialog(null,mensaje.plus(" $autor"),
            "Sobre Editor avanzado", JOptionPane.INFORMATION_MESSAGE)

    }
}


fun main(args: Array<String>) {
    EventQueue.invokeLater({ Exercici_2_3().isVisible = true })
}

