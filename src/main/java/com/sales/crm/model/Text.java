//package com.sales.crm.model;
//
//public class Text {
//}


//UX/UI desinger - customer journey

// Aplicatie pentru vanzatori de gestionare a clientilor si produselor vandute

    //Rolul aplicatiei: facilitatea managementului comenzilor de mobilier
    // tipul utilizatorilor: vanzatori, administratori, technical, measurement, production
    // un user are mai multi clienti (ok)
    // un client are mai multe oferte (ok)
    // o oferta este legata de un singur order si un order are o singura oferta (ok)
    // un client are mai multe order (ok)
    // o oferta are mai multe produse si un nume , un produs apartie mai multor oferte (ok)
    // o lucrare are o lucrare, care are mai multe produse -->> new
    // o lucrare are mai multe produse si un nume, un produs apartie mai multor lucrari (
    // o categorie are mai multe produse (ok)
    // un client/o oferta are mai multe observatii de vanzare (text, data crearii, tip)
    // un order are mai multe observatii de lucrare (ok)
    //un client are mai multe intalniri(OK)
    //un user are mai multe intalniri(ok)
    // o intalnire are mai multe observatii de vanzare, o obs de vanzare apartine unei intalniri (ok)
    // utilizatorul are o lista de notificari legate de organizare  ?????

    //Functionalitati utilizatori sales:
        // adaugare clienti
        // modificare date clienti
        // adaugare order (lucrare) client (implica si factura pdf)
            //order status:
                //created
                //measurements - schimbat de measurement role
                //client_feedback - schimbat de sales role
                //proiectare - schimbare de techincal role
                //achizitie - schimbat de production role
                //productie - schimbat de production role
                //montaj - schimbat de production role
                //done - schimbat de sales role
        // modificare status offer in accepted (implica generare de contract si se creaza un order)
        // adagaure observatie de vanzare clientului
        // adaugare observatie de lucrare
        // adaugare intalnire cu clientul
        // transformarea audio-ului inregistrarii intalnirii in text cu AI si analiza ei cu AI
        // trimitere notificari catre client in sceventa, incepand de la momentul adaugarii intalnirii cu clientul - kafka
        // primire notificare (reminder calendar) in functie de observatiile adaugate unui client
        // primire notificare (reminder calendar) in functie de intalnire cu clientul
        // generare de oferta
        // vizualizare clienti proprii cu oferte/ clienti cu contracte
        // inregistrare in urma unei invitatii
        // modificare status order daca are permisiunea



    //Functionalitati utilizatori admin:
        //adaugare alti utilizatori (sales sau technical sau measurment sau production) -> trimitere mail cu invitatie
        //vizualizare activitate vanzator
        // vizualizare clienti cu oferte/ clienti cu contracte
        // vizualizare suma totala vanduta pe o anumita perioada, etc.
        //adaugare produs/categorie
    //functionalitati technical/production/etc.
        //inregistrare in urma unei invitatii
        //modficiare status order daca are permisiiunea
        //vad observatiile unei lucrari
        //adaugare observatie de lucrare


//pentru a putea functiona, aplicatia trebuie sa se extinda si pentru parte de urmarire a comenzii in productie


