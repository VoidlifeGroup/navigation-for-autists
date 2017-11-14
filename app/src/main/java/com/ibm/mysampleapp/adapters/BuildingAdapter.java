package com.ibm.mysampleapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ibm.mysampleapp.Building;
import com.ibm.mysampleapp.R;

import java.util.ArrayList;

/**
 * BuildingAdapter je classa ktorá rozširuje klasický ArrayAdapter ktorý slúži na vpisovanie
 * textov a obrázkov do ListViewu. Klasický ArrayAdapter nepodporuje prácu s ArrayListom
 * vlastných objektov preto musel byť vytvorený vlastný Adapter, upravený tak aby vypisoval
 * všetky potrebné veci objektu. BuildingAdapter konkrétne vracia cez View názov budovy do riadka
 * listu, grafická implementácia je spracovaná v /res/layout/row_item.xml a výzor listu
 * v /res/layout/content_main.xml.
 *
 * @author Marek Baláž
 */

public class BuildingAdapter extends ArrayAdapter<Building> {

    private Context mContext;
    private int lastPosition = -1;

    /**
     * Konštruktor slúži na načítanie dát z ArrayListu budov a všetkých dát z classy ktorá volá
     * tento konšturktor. Implementuje sa tu aj layout výzoru riadka listu.
     * @param data      načítanie dát ArrayListu
     * @param context   načítanie všetkých dát z classy ktorá volá tento konštruktor
     */
    public BuildingAdapter(ArrayList<Building> data, Context context) {
        super(context, R.layout.row_item, data);
        this.mContext = context;
    }


    /**
     * Metóda getView je automaticky volaná pri použití tohto adaptéru a pri výpise jednotlivých
     * riadkov zoznamu. Nahradí klasický View. Najprv sa načíta pozícia prvku v zozname,
     * skontroluje či už existuje daný View preto sa použije viewHolder ktorý ukladá View do
     * vyrovnávacej pamäte tagu. Ak neexistuje tak sa načíta (inflate) View. Teda do convertView sa
     * načíta layout riadka, do vyrovnávacej pamäte sa načíta všetko čo sa má zobraziť a všetko sa
     * uloží do tagu. Ak už View existuje tak sa všetko načíta z tagu. V metóde je implementovaná
     * animácia listu ktorej údaje sú v /res/anim/. Ako posledné sa nastaví spôsob vypisovania
     * objektu ArrayListu a vráti sa kompletný vlastný View.
     *
     * @param position      pozícia objektu v ArrayListe
     * @param convertView   znovu použitý už vytvorený a uložený View
     * @param parent        ukladá parametre rozloženia daného View
     * @return              vráti kompletné View, ktoré sa vyrenderuje na obrazovku
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Building buildingData = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ?
                R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(buildingData != null ? buildingData.getName() : null);
        return convertView;
    }

    /**
     * Vytvorenie vyrovnávacej pamäte pre View.
     */
    private static class ViewHolder {
        TextView txtName;
    }

}
