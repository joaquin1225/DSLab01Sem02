import java.util.*;

public class Enrutamiento {
    static String matchRoute(String routePath, String routeCont, String transicion){
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
                String p = r.substring(1);
                cont = cont.replace("{"+p+"}", t);
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

        for(int i=0; i<n; i++){
            String route = sc.nextLine();
            String[] parts = route.split(" ",2);
            rutas.add(new String[]{parts[0], parts[1]});
        }

        int m = Integer.parseInt(sc.nextLine());

        for(int i=0; i<m; i++){
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