package com.hendisantika.adminlte.util;




import java.util.ArrayList;


import java.util.List;

import com.hendisantika.adminlte.model.Personne;
import com.hendisantika.adminlte.service.PersonneService;






public class AddActors {



    public static List<Personne> stringToPersonne(String  ids,PersonneService personneService) {

        List<Personne> personnes = new ArrayList<Personne>();

        String[] tableId = ids.split(",");

        for(int i=0;i<ids.length();i++) {

            try {

                Personne personne = personneService.get(Long.valueOf(tableId[i]));

                personnes.add(personne);

            }catch(Exception e) {

               

            }

        }

        return personnes;

    }

}