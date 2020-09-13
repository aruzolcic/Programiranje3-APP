package ffos.aruzolcic_19.programskiJezici;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private AdapterProgramskiJezici adapter;
    private RESTTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.programskiJeziciiLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterProgramskiJezici(this);
        recyclerView.setAdapter(adapter);

        SearchView searchView = (SearchView) findViewById(R.id.trazilica);

        searchView.setQueryHint("Traži u ontologiji...");
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                asyncTask = new RESTTask();
                if(query.toLowerCase().equals("jezik")){
                    asyncTask.execute(getString(R.string.REST_URL));
                }else{
                    asyncTask.execute( "https://oziz.ffos.hr/nastava20192020/aruzolcic_19/ontologija/search/" + query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    private class RESTTask extends AsyncTask<String, Void, List<ProgramskiJezici>> {
        protected List<ProgramskiJezici> doInBackground(String... adresa) {
            String stringUrl = adresa[0];
            List<ProgramskiJezici> vrati = null;
            try {
                URL myUrl = new URL(stringUrl);
                HttpURLConnection connection = (HttpURLConnection)
                        myUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(15000);
                connection.setConnectTimeout(15000);
                connection.connect();
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                Type listType = new TypeToken<ArrayList<ProgramskiJezici>>() {
                }.getType();
                vrati = new Gson().fromJson(reader, listType);
                reader.close();
                streamReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return vrati;
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(List<ProgramskiJezici> podaci1) {
            adapter.setPodaci(podaci1);
            adapter.notifyDataSetChanged();
        }

    }


}