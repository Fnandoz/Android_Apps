package fernando.databaseteste;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fernando.databaseteste.helper.DatabaseHelper;
import fernando.databaseteste.model.Estados;
import fernando.databaseteste.model.Municipios;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper helper;

    EditText ufEstado, nomeEstado, nomeMunicipio;
    TextView ufMunicipio;
    Button salvarEstado, listarEstados, salvarMunicipio, listarTodosEstados, listarTodosMunicipios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DatabaseHelper(getApplicationContext());

        ufEstado = (EditText) findViewById(R.id.ufEstadoEdit);
        nomeEstado = (EditText) findViewById(R.id.nomeEstadoEdit);
        nomeMunicipio = (EditText) findViewById(R.id.nomeMunicipioEdit);

        ufMunicipio = (TextView) findViewById(R.id.ufMunicipioText);

        salvarEstado = (Button) findViewById(R.id.salvarEstado);
        listarEstados = (Button) findViewById(R.id.listaEstadosBtn);
        salvarMunicipio = (Button) findViewById(R.id.salvarMunicipioBtn);

        listarTodosEstados = (Button) findViewById(R.id.listarEstadosbutton);
        listarTodosMunicipios = (Button) findViewById(R.id.listarMunicipiosbutton);

        salvarEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome, uf;
                nome = nomeEstado.getText().toString();
                uf = ufEstado.getText().toString();

                if(TextUtils.isEmpty(nome)){
                    nomeEstado.setError("Preencha este campo");
                }
                if(TextUtils.isEmpty(uf)){
                    nomeEstado.setError("Preencha este campo");
                }

                if(!nome.isEmpty() && !uf.isEmpty()){
                    Estados estado = new Estados(nome, uf);
                    helper.criaEstado(estado);
                    nomeEstado.setText("");
                    ufEstado.setText("");
                    Toast.makeText(MainActivity.this, "Estado salvo com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listarEstados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Estados> estadosl = helper.retornaTodosEstados();
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);

                for(Estados e:estadosl){
                    arrayAdapter.add(e.getNome());
                }


                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Escolha um estado");
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ufMunicipio.setText(arrayAdapter.getItem(i));
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        salvarMunicipio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome, uf;
                nome = nomeMunicipio.getText().toString();
                uf = ufMunicipio.getText().toString();

                if(TextUtils.isEmpty(nome)){
                    nomeMunicipio.setError("Preencha este campo");
                }
                if(uf.equals("-")){
                    ufMunicipio.setError("");
                }

                if(!nome.isEmpty() && !uf.isEmpty()){
                    Municipios municipio = new Municipios(nome, uf);
                    helper.criaMunicipio(municipio);
                    nomeMunicipio.setText("");
                    ufMunicipio.setText("");
                    ufMunicipio.setError(null);
                    Toast.makeText(MainActivity.this, "Município salvo com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        listarTodosEstados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Estados");
                builder.setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.setNeutralButton("Fechar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        listarTodosMunicipios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Municípios");
                builder.setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.setNeutralButton("Fechar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });


    }
}
