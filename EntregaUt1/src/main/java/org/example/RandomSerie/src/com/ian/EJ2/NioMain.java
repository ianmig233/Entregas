package com.ian.EJ2;

/*2. Realiza una aplicación, haciendo uso de las clases de NIO, incluyendo buffers y canales,
para hacer una copia del fichero de acceso directo generado en la anterior aplicación.*/

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

public class NioMain {

    public static void main(String[] args) {

        ByteBuffer buffer;
        FileChannel fcw;

        try (RandomAccessFile raf = new RandomAccessFile("registros.dat", "rw");
        FileChannel fcr = raf.getChannel()) {

            //Lectura de fichero
            Path path = Paths.get("copia_series.dat");
            Set<StandardOpenOption> opciones = new HashSet<>();
            opciones.add(StandardOpenOption.CREATE);
            opciones.add(StandardOpenOption.APPEND);

            //Escritura del fichero
            fcw = FileChannel.open(path, opciones);
            buffer = ByteBuffer.allocate(512);

            while (fcr.read(buffer) > 0) {

                buffer.flip();
                fcw.write(buffer);
                buffer.rewind();

            }
            fcw.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
