/*
* Este programa determina al cliente mas fiel de cada socio de un banco.
* Para ello usa estructuras HashMap, lo que permite relacionar facilmente cada terminal con su socio, y cada socio con
* sus clientes y la cantidad de transacciones realizadas por cada cliente.
* */
import java.util.*;

public class Banco {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // # de socios
        int m = sc.nextInt(); // # de terminales
        int s = sc.nextInt(); // # de transacciones

        Map<Integer,Integer> terminalSocio = new HashMap<>(); //(id_terminal,id_socio)
        for(int i=0;i<m;i++){
            int p = sc.nextInt(); //socio
            int t = sc.nextInt(); //terminal
            terminalSocio.put(t,p);
        }

        Map<Integer,Map<Integer,Integer>> contar = new HashMap<>(); //(id_socio,(id_cliente,cantidad_transacciones))
        for(int i=0;i<s;i++){
            int c = sc.nextInt(); //cliente
            int t = sc.nextInt(); //terminal

            if(!terminalSocio.containsKey(t)) continue; //Si encuentra un id_terminal que no tiene un socio asignado, lo omite

            int socio = terminalSocio.get(t); //Recupera el id de socio asignado a la terminal donde el cliente realiza su compra

            contar.putIfAbsent(socio,new HashMap<>());
            Map<Integer,Integer> clientes = contar.get(socio);
            clientes.put(c,clientes.getOrDefault(c,0)+1); //Incrementa la cantidad de transacciones respecto a un id_cliente
        }

        Set<Integer> socios = new TreeSet<>(terminalSocio.values()); //Uso un TreeSet para mantener el orden de los socios al imprimir los resultados
        for(int socio : socios){
            //Si no se realiza ninguna compra en el terminal asociado al socio, imprime "-1"
            if(!contar.containsKey(socio)){
                System.out.println(socio+" -1");
                continue;
            }
            Map<Integer,Integer> clientes = contar.get(socio);
            int mejorCliente = 0; //Guarda el id del cliente con mas compras, se inicializa en 0
            int maxCompras = 0; //Guarda la cantidad de compras del cliente con mas compras, se inicaliza en 0
            for(Map.Entry<Integer,Integer> entry:clientes.entrySet()){
                int cliente = entry.getKey();
                int numCompras = entry.getValue();
                if(numCompras > maxCompras || (numCompras == maxCompras && cliente < mejorCliente)){
                    maxCompras = numCompras;
                    mejorCliente = cliente;
                }
            }
            System.out.println(socio + " " + mejorCliente);
        }
        sc.close();
    }
}
