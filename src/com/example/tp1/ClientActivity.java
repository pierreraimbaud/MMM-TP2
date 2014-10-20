package com.example.tp1;

import java.util.ArrayList;
import java.util.HashMap;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ClientActivity extends ActionBarActivity {

	private ListView maListViewPerso;
	private SimpleAdapter mListAdapter;
	private ArrayList<HashMap<String, String>> listItem;
	private HashMap<String, String> map;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("creer1");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.client_activity);

		//Récupération de la listview créée dans le fichier client_activity.xml
		maListViewPerso = (ListView) findViewById(R.id.listviewperso);

		//Création de la ArrayList qui nous permettra de remplire la listView
		listItem = new ArrayList<HashMap<String, String>>();

		//On déclare la HashMap qui contiendra les informations pour un item
		HashMap<String, String> map;

		//Création d'une HashMap pour insérer les informations du premier item de notre listView
		map = new HashMap<String, String>();

		//On refait la manip plusieurs fois avec des données différentes pour former
		//les items de notre ListView

		map = new HashMap<String, String>();
		map.put("Nom", "Withoutsoucy");
		map.put("Prenom", "Martine");
		//map.put("img", String.valueOf(R.drawable.ic_launcher));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("Nom", "Poney");
		map.put("Prenom", "Karine");
		//map.put("img", String.valueOf(R.drawable.ic_launcher));
		listItem.add(map);

		//Création d'un SimpleAdapter qui se chargera de mettre
		//les items présent dans notre list (listItem) dans la vue affichageitem

		mListAdapter = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.listviewitem,
				//new String[] {"img", "Nom", "Prenom"}, new int[] {R.id.img, R.id.Nom, R.id.Prenom});
				new String[] {"Nom", "Prenom"}, new int[] {R.id.Nom, R.id.Prenom});

		//On attribue à notre listView l'adapter que l'on vient de créer
		maListViewPerso.setAdapter(mListAdapter);
		displayIntentData();
		maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				//on récupère la HashMap contenant les infos de notre item (Nom, Prenom, img)
				@SuppressWarnings("unchecked")
				HashMap<String, String> map = 
				(HashMap<String, String>) maListViewPerso.getItemAtPosition(position);
				//on créer une boite de dialogue
				AlertDialog.Builder adb = new AlertDialog.Builder(ClientActivity.this);
				//on attribut un titre à notre boite de dialogue
				adb.setTitle("Sélection Item");
				//on insère un message à notre boite de dialogue, et ici on affiche le titre de l'item cliqué
				adb.setMessage("Votre choix : "+map.get("Nom"));
				//on indique que l'on veut le bouton ok à notre boite de dialogue
				adb.setPositiveButton("Ok", null);
				//on affiche la boite de dialogue
				adb.show();
			}

		});
	}

	private void displayIntentData(){
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();

		if(extras!=null){
			Repertoire r = extras.getParcelable("key");
			String nom;
			String prenom;
			//String date; 
			//String ville;
			nom = r.getNom().trim();
			prenom = r.getPrenom().trim();
			//date = r.getDate();
			//ville = r.getVille();

			//Création d'une HashMap pour insérer les informations du premier item de notre listView
			map = new HashMap<String, String>();

			//on insère un élément titre que l'on récupérera dans le textView titre 
			//créé dans le fichier affichageitem.xml
			map.put("Nom", nom);
			//on insère un élément description que l'on récupérera dans le textView description créé 
			//dans le fichier affichageitem.xml
			map.put("Prenom", prenom);			
			//map.put("img", String.valueOf(R.drawable.ic_launcher));

			//enfin on ajoute cette hashMap dans la arrayList
			listItem.add(map);

			mListAdapter.notifyDataSetChanged();

			//Création d'un SimpleAdapter qui se chargera de mettre
			//les items présent dans notre list (listItem) dans la vue affichageitem

			mListAdapter = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.listviewitem,
					//new String[] {"img", "Nom", "Prenom"}, new int[] {R.id.img, R.id.Nom, R.id.Prenom});
					new String[] {"Nom", "Prenom"}, new int[] {R.id.Nom, R.id.Prenom});

			//On attribue à notre listView l'adapter que l'on vient de créer
			maListViewPerso.setAdapter(mListAdapter);

		}
		else{// nothing
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);//must store the new intent unless getIntent() will return the old one
		displayIntentData();
	}

	public void addItem(View v) {
		Intent intent = new Intent(ClientActivity.this, MainActivity.class);
		startActivity(intent);
		//notice we dont call finish() here
	}
}