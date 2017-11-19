package com.ibm.mysampleapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ibm.mysampleapp.R;
import com.ibm.mysampleapp.core.Building;
import com.ibm.mysampleapp.graph.Room;

import java.util.ArrayList;

/**
 * RoomAdapter je classa ktorá rozširuje klasický ArrayAdapter ktorý slúži na vpisovanie
 * textov a obrázkov do ListViewu. Klasický ArrayAdapter nepodporuje prácu s ArrayListom
 * vlastných objektov preto musel byť vytvorený vlastný Adapter, upravený tak aby vypisoval
 * všetky potrebné veci objektu. RoomAdapter konkrétne vracia cez View názov miestnosti do riadka
 * listu, grafická implementácia je spracovaná v /res/layout/row_item.xml a výzor listu
 * v /res/layout/room_menu.xml.
 *
 * @author Marek Baláž
 */
public class RoomAdapter extends ArrayAdapter<Room> implements Filterable{

    private ArrayList<Room> mOriginalValues;
    private ArrayList<Room> mDisplayedValues;
    private RoomFilter mFilter = new RoomFilter();

    private Context mContext;
    private int lastPosition = -1;

    /**
     * Konštruktor slúži na načítanie dát z ArrayListu miestností a všetkých dát z classy ktorá
     * volá tento konšturktor. Implementuje sa tu aj layout výzoru riadka listu.
     *
     * @param data    načítanie dát ArrayListu
     * @param context načítanie všetkých dát z classy ktorá volá tento konštruktor
     */
    public RoomAdapter(ArrayList<Room> data, Context context) {
        super(context, R.layout.row_item, data);
        this.mContext = context;
        this.mOriginalValues = data;
        this.mDisplayedValues = data;
    }

    /**
     * Vracia prvok v ArrayListe typu Room na danej pozicií.
     *
     * @param position pozícia prvku v ArrayListe
     * @return vracia porvok na danej pozicií
     */
    public Room getItem(int position) {
        return mDisplayedValues.get(position);
    }

    /**
     * Zistí veľkosť ArrayListu a vráti hodnotu.
     *
     * @return vracia veľkosť daného ArrayListu
     */
    public int getCount() {
        return mDisplayedValues.size();
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
     * @param position    pozícia objektu v ArrayListe
     * @param convertView znovu použitý už vytvorený a uložený View
     * @param parent      ukladá parametre rozloženia daného View
     * @return vráti kompletné View, ktoré sa vyrenderuje na obrazovku
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        Room roomData = getItem(position);
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


        viewHolder.txtName.setText(roomData != null ? roomData.getName() : null);
        return convertView;
    }

    /**
     * Metóda ktorá vracia filter ktorý sa použije.
     *
     * @return vracia filter typu BuildingFilter
     */
    @NonNull
    public Filter getFilter() {
        return mFilter;
    }

    /**
     * Vytvorenie vyrovnávacej pamäte pre View.
     */
    private static class ViewHolder {
        TextView txtName;
    }

    /**
     * Classa slúži na implementovanie filtra, ktorým sa za pomoci editTextu získa string ktorým
     * sa porovnávajú či sa daný string nachádza v menách miestností. Ak sa nachádza zapíše ho do
     * nového listu ktorý sa potom použije ako ArrayList ktorý ma zobraziť ListView.
     */
    private class RoomFilter extends Filter {
        /**
         * Algoritmus ktorý porovnáva výskit zadaného stringu s názvami budou, ktoré potom zapisuje
         * do nového listu ktorý sa potom vracia cez return results.
         *
         * @param constraint string získaný z editTextu, ktoré zadáva používateľ
         * @return vracia už odfiltrované hodnoty (veľkosť a ArrayList)
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            int count = mOriginalValues.size();
            final ArrayList<Room> nlist = new ArrayList<>(count);

            for (int i = 0; i < count; i++) {
                if (mOriginalValues.get(i).getName().toLowerCase().contains(filterString)) {
                    nlist.add(new Room(mOriginalValues.get(i).getId(),
                            mOriginalValues.get(i).getEdges(), mOriginalValues.get(i).getName()));
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        /**
         * Metóda sa spúšťa na aktualizovanie UI ak nastala zmena napríklad užívateľ zadal ďalšie
         * písmeno do vyhľadávania.
         *
         * @param constraint string získaný z editTextu, ktoré zadáva používateľ
         * @param results    vracia už odfiltrované hodnoty ArrayListu
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mDisplayedValues = (ArrayList<Room>) results.values;
            notifyDataSetChanged();
        }

    }
}
