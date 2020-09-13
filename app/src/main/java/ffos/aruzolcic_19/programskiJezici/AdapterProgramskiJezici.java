package ffos.aruzolcic_19.programskiJezici;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class AdapterProgramskiJezici extends RecyclerView.Adapter<AdapterProgramskiJezici.Red>  {

    private List<ProgramskiJezici> podaci1;
    private List<ProgramskiJezici> podaci2;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public AdapterProgramskiJezici(Context context) {
        this.mInflater = LayoutInflater.from(context);
        podaci1 = new ArrayList<>();
    }


    @Override
    public Red onCreateViewHolder(ViewGroup roditelj, int viewType) {
        podaci2 = new ArrayList<>(podaci1);
        View view = mInflater.inflate(R.layout.red_liste, roditelj, false);
        return new Red(view);
    }


    @Override
    public void onBindViewHolder(Red red, int position) {
        ProgramskiJezici pj = podaci1.get(position);

        red.naziv.setText(pj.getNaziv());
        red.tvorac.setText(pj.getTvorac());
        red.programskaParadigma.setText(pj.getProgramskaParadigma());
        red.opis.setText(pj.getOpis());

    }

    @Override
    public int getItemCount() {
        return podaci1==null ? 0 : podaci1.size();
    }


    public class Red extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView naziv;
        private TextView tvorac;
        private TextView programskaParadigma;
        private TextView opis;

        Red(View itemView) {
            super(itemView);

            naziv = itemView.findViewById(R.id.naziv);
            tvorac = itemView.findViewById(R.id.tvorac);
            programskaParadigma = itemView.findViewById(R.id.paradigma);
            opis = itemView.findViewById(R.id.opis);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


    public ProgramskiJezici getItem(int id) {
        return podaci1.get(id);
    }

    public void setPodaci(List<ProgramskiJezici> itemList) {
        this.podaci1 = itemList;
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


};



