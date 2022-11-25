package org.example.RandomSerie.src.com.ian.EJ1;

import java.io.*;
import java.util.Scanner;


public class RandomMain {
    public static Scanner tec = new Scanner(System.in);
    public static int size = 50;

    public static int cod2;

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
                        System.out.println("Introduzca un código de la pelicula deseada:");
                        cod2 = tec.nextInt();
                        Devolver(cod2);break;

                    case 3:
                        System.out.println("Mostramos el duplicado");
                        LeerDuplicados();break;

                    case 0:
                        System.exit(0);

                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public static void Ingresar(){

        try(RandomAccessFile raf = new RandomAccessFile("registro.dat", "rw")){
            System.out.println("Introduce el Codigo de la película: ");
            cod2= tec.nextInt();
            System.out.println("Introduce el Nombre: ");
            String nombre2= tec.nextLine();
            System.out.println("Introduce la cantidad de temporadas que tiene: ");
            int temporadas2= tec.nextInt();
            tec.nextLine();

            if(raf.readInt() == cod2)
                Duplicados(cod2,nombre2,temporadas2);

            else {
            raf.seek((cod2*size) - 1 );
            raf.writeInt(cod2);
            raf.writeUTF(nombre2);
            raf.writeInt(temporadas2);

            System.out.println("Se ha agregado con exito.\n");}


        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void Devolver(int cod2){

            int cod = cod2;
            String nombre;
            int temporadas;

        try(RandomAccessFile raf = new RandomAccessFile("registro.dat", "rw")){

            raf.seek((cod*size) - 1 );
            cod = raf.readInt();
            nombre = raf.readUTF();
            temporadas = raf.readInt();

            System.out.println("La serie con el Codigo: "+cod+"\n Se llama: "+nombre+"\n Tiene un total de : "+temporadas+" temporadas\n");

        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    public static void Duplicados(int cod2, String nombre2, int temporadas2){

        System.out.println("Los duplicados encontrados son");

        try (DataOutputStream ds = new DataOutputStream(new FileOutputStream("duplicados.dat", true))) {

            ds.writeInt(cod2);
            ds.writeUTF(nombre2);
            ds.writeInt(temporadas2);



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static  void LeerDuplicados(){

        int cod2;
        String nombre2;
        int temporadas2;

        try (DataInputStream di = new DataInputStream(new FileInputStream("duplicados.dat"))) {

            while (di.read()>0){
                cod2 = di.readInt();
                nombre2 = di.readUTF();
                temporadas2 = di.readInt();

                System.out.println("La serie con el Codigo: "+cod2+"\n Se llama: "+nombre2+"\n Tiene un total de : "+temporadas2+" temporadas\n");
            }



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}