/*
 * Este programa simula un sistema de enrutamiento de rutas, donde se definen rutas y su contenido, con posibles
 * parámetros, y se comparan con transiciones para determinar si hay una coincidencia. Si una ruta coincide con una
 * transición, se reemplazan los parámetros en la ruta con los valores correspondientes de la transición y se imprime
 * el resultado. Si no hay coincidencia, se imprime "404 Not Found".
 * */
import java.util.*;

public class Enrutamiento {
    static String matchRoute(String routePath, String routeCont, String transicion){
        //Elimina las barras al inicio y al final de la ruta, y luego divide la ruta en partes usando la barra como separador
        String[] routeParts = routePath.replaceAll("^/|/$", "").split("/");
        String[] transicionParts = transicion.replaceAll("^/|/$", "").split("/");

        if(routePath.equals("/") && transicion.equals("/")){
            return routeCont;
        }
        if(routeParts.length != transicionParts.length){
            return null;
        }

        String cont = routeCont;

        for(int i=0; i<routeParts.length; i++){
            String r = routeParts[i];
            String t = transicionParts[i];
            if(r.startsWith(":")){
                String p = r.substring(1); //Obtiene el nombre del parametro eliminando ":" al inicio
                cont = cont.replace("{"+p+"}", t); //Reemplaza los parametros en el contenido de la ruta
            } else if(!r.equals(t)){
                return null;
            }
        }

        return cont;
    }

    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        List<String[]> rutas = new ArrayList<>();
        List<String> salidas = new ArrayList<>();

        for(int i=0; i<n; i++){ //Lee las rutas
            String route = sc.nextLine();
            String[] parts = route.split(" ",2); //Divide la entrada en dos partes: la ruta (routePath) y el contenido (routeCont).
            rutas.add(new String[]{parts[0], parts[1]});
        }

        int m = Integer.parseInt(sc.nextLine());

        for(int i=0; i<m; i++){ //Lee las transiciones
            String transicion = sc.nextLine();
            boolean found = false;
            for(String[] r: rutas){
                String res = matchRoute(r[0],r[1],transicion);
                if(res != null){
                    salidas.add(res);
                    found = true;
                    break;
                }
            }
            if(!found){
                salidas.add("404 Not Found");
            }
        }

        for(String s: salidas){
            System.out.println(s);
        }
    }
}