/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.main;

import edu.eci.arsw.blueprints.filtroInter.Filtro;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author User
 */
public class Main {
    
    public static void main(String a[]) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = ac.getBean(BlueprintsServices.class);

        Point[] ptsJp=new Point[]{new Point(88, 45),new Point(39, 64),new Point(1254,546),new Point(7,8),new Point(4,2),new Point(11,22)};
        Blueprint bpJp=new Blueprint("JuanPablo", "planoX",ptsJp);
        
        Point[] pts=new Point[]{new Point(0, 1),new Point(1, 0)};
        Blueprint bp=new Blueprint("Stefany", "planoY",pts);
        
        Point[] pts2=new Point[]{new Point(50, 1),new Point(1, 05)};
        Blueprint bp2=new Blueprint("Stefany", "planoYXYXY",pts2);
        
        //Adicionar nuevos Planos
         
        try {
            bps.addNewBlueprint(bp);
            bps.addNewBlueprint(bpJp);
            bps.addNewBlueprint(bp2);
        } catch (BlueprintPersistenceException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        //Consultar Plano
        try {
            System.out.println(bps.getBlueprintsByAuthor("Stefany"));
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Consultar plano Especifico
        try {
            System.out.println(bps.getBlueprint("JuanPablo", "planoX"));
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        bpJp = bps.filtrar(bpJp);
        for(int i=0; i<bpJp.getPoints().size(); i++){
            System.out.println("X:"+bpJp.getPoints().get(i).getX()+"Y:"+bpJp.getPoints().get(i).getY());
        }
    }
    
}
