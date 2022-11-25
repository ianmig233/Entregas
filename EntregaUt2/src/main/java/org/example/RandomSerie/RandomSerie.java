package org.example.RandomSerie;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;


public class RandomSerie {
    public static Scanner tec = new Scanner(System.in);
    public static int size = 50;


    public static void main(String[] args) {


        try(RandomAccessFile raf = new RandomAccessFile("registro.dat", "rw")){
            raf.setLength(5000);

            while(true){
                System.out.println("Que necesitas hacer:\n - Dar de alta a una Serie----> 1\n - Mostrar La serie por su cod-----> 2\n- Salir del programa 0");

                int opcion = tec.nextInt();
                switch(opcion){
                    case 1:
                        Ingresar();break;

                    case 2:
                        int cod = tec.nextInt();
                        String nombre = tec.nextLine();
                        int temporadas = tec.nextInt();
                        Devolver(cod,nombre,temporadas);break;

                    case 0:
                        System.exit(0);

                }

            }
        } catch (Exception e) {

        }

    }
    public static void Ingresar(){

        try(RandomAccessFile raf = new RandomAccessFile("registros.dat", "rw")){
            System.out.println("Introduce el Codigo de la película: ");
            int cod2= tec.nextInt();
            tec.nextLine();
            System.out.println("Introduce el Nombre: ");
            String nombre2= tec.nextLine();
            System.out.println("Introduce la cantidad de temporadas que tiene: ");
            int temporadas2= tec.nextInt();
            tec.nextLine();



            raf.seek((cod2*size) - 1 );
            raf.writeInt(cod2);
            raf.writeUTF(nombre2);
            raf.writeInt(temporadas2);

            System.out.println("Se ha agregado con exito.\n");


        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void Devolver(int cod, String nombre, int temporadas){
        int cod2 = cod;
        String nombre2 = nombre;
        int temporadas2 = temporadas;

        System.out.println("Escriba el código de la serie que busca: ");

        try(RandomAccessFile raf = new RandomAccessFile("registros.dat", "rw")){

            raf.seek((cod2*size) - 1 );
            cod2 = raf.readInt();
            nombre2 = raf.readUTF();
            temporadas2 = raf.readInt();

            System.out.println("La serie con el Codigo: "+cod2+"\n Se llama: "+nombre2+"\n Tiene un total de : "+temporadas2+" temporadas\n");

        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}