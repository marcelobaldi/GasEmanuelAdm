package br.com.gasemanueladm.listaPendEntrega;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.gasemanueladm.R;
import br.com.gasemanueladm.detalhePedido.DetalhePedidoTela;
import br.com.gasemanueladm.listaPendAceite.ListaPendAceiteDados;
import br.com.gasemanueladm.listaPendAceite.ListaPendAceiteTela;

public class ListaPendEntregaTela extends AppCompatActivity {

    //Declarar e Instanciar Classes Firebase
    private FirebaseAuth     fireAuth   = FirebaseAuth.getInstance();
    private FirebaseDatabase fireDados  = FirebaseDatabase.getInstance();

    //Variáveis Usadas Em Toda Classe
    private String           idUsuario;

    //Objetos Lista
    private ListView         listaObjeto;
    private ArrayList<ListaPendEntregaDados>   listaValores = new ArrayList<>(  );
    private ClasseAdapter listaModeloAdapter = new ClasseAdapter();


    //Método - Inicial
    @Override protected void onCreate(Bundle savedInstanceState){super.onCreate(savedInstanceState);setContentView(R.layout.lista_pend_entrega );

        //Lista - Carregar o Conteúdo do Banco de Dados (A Lista de Pedidos Pendentes)
        fireBuscarPedido();

        //Lista - Montar
        listaObjeto = findViewById(R.id.ListaPendAceiteXml );
        listaModeloAdapter.listaAdpter = listaValores;
        listaObjeto.setAdapter( listaModeloAdapter );

        //Lista - Ação Nos Itens
        listaObjeto.setOnItemClickListener( new AdapterView.OnItemClickListener() {
        @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    //        Toast.makeText( this, listaValores.get( position ).getPedHora(), Toast.LENGTH_SHORT ).show();

            Intent intent = new Intent(ListaPendEntregaTela.this, DetalhePedidoTela.class);
   //         intent.putExtra("Item", listaValores.get(position));
            startActivity( intent );

        }});
    }

    //Modelo da Lista
    class ClasseAdapter extends BaseAdapter {
        ArrayList<ListaPendEntregaDados> listaAdpter = new ArrayList<>(  );

        @Override public int getCount()               { return listaAdpter.size();}
        @Override public Object getItem(int position) { return listaAdpter.get(position);}
        @Override public long getItemId(int position) { return position;}

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate( R.layout.lista_pend_entrega_adpt, null );
            TextView nomeCliente = convertView.findViewById( R.id.nomeCliente2Xml );
            TextView horaPedido  = convertView.findViewById( R.id.horaPedido2Xml );

            nomeCliente.setText( listaAdpter.get(position).getPedNomeCliente() );
            horaPedido.setText( listaAdpter.get( position).getPedHora() );

            return convertView;
        }
    }


    //Método - Buscar Dados Parte 1 (Onde Buscar)
    public void fireBuscarPedido(){

        final ChildEventListener listaPendentes = new ChildEventListener() {                                //Firebase - Classe
            @Override public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {    //Firebase - Método Quando Add;

                idUsuario = dataSnapshot.getKey().toString();                                       //É o SubNó da Tabela
                fireBuscarDetalhesPedido(idUsuario);

            }
            @Override public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
            @Override public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override public void onCancelled(@NonNull DatabaseError databaseError) {}

        };

        //Onde Buscar (Nós da Tabela Pedidos)
        DatabaseReference tabelaPedidos = fireDados.getReference().child("Pedidos");
        tabelaPedidos.addChildEventListener(listaPendentes);
    }


    //Método - Buscar Dados Parte 2 (O que Buscar)
    public void fireBuscarDetalhesPedido(String id) {

        //Classe EventListener (Melhor que a Value EventListener)
        ChildEventListener childEventListener = new ChildEventListener() {

            //Método Adicionar Desta Classe
            @Override public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                ListaPendEntregaDados novoDado = new ListaPendEntregaDados();

                //Campos do Firebase
                String cliNome      = dataSnapshot.child( "CliNome" ).getValue().toString();
                String pedData      = dataSnapshot.child( "PedData" ).getValue().toString();
                String pedHora      = dataSnapshot.child( "PedHora" ).getValue().toString();
                String pedObs       = dataSnapshot.child( "PedObs" ).getValue().toString();

                String pedStatus    = dataSnapshot.child( "PedStatus" ).getValue().toString();

                String pedValorTot  = dataSnapshot.child( "PedValorTot" ).getValue().toString();
                String prodNome     = dataSnapshot.child( "ProdNome" ).getValue().toString();
                String prodModelo   = dataSnapshot.child( "ProdModel" ).getValue().toString();
                String prodQt       = dataSnapshot.child( "ProdQt" ).getValue().toString();
                String prodVUnit    = dataSnapshot.child( "ProdVUnit" ).getValue().toString();

                //Adicionar Valor a Classe. Fazer If's, Para Se Tiver em Branco no Firebase, Não Der Erro
                novoDado.setPedIdCliente( idUsuario );
                novoDado.setPedNomeCliente( cliNome );
                novoDado.setPedData( pedData );
                novoDado.setPedHora( pedHora );
                novoDado.setPedObserv( pedObs );
                novoDado.setPedStatus( pedStatus );
                novoDado.setPedValorTotal( pedValorTot );
                novoDado.setProdNome( prodNome );
                novoDado.setProdModelo( prodModelo );
                novoDado.setProdGasQuant( prodQt );
                novoDado.setProdGasValorUnit( prodVUnit );

                //A Condição Do Campo
                if (pedStatus.equals( "Aceito" )){
                    listaValores.clear();                               //Para Não Duplicar a Lista;

                    //Adicionar na Lista
                    listaValores.add(novoDado);

                    //Atualizar Lista
                    listaModeloAdapter.notifyDataSetChanged();
                }

            }
            @Override public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
            @Override public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override public void onCancelled(@NonNull DatabaseError databaseError) { }
        };

        DatabaseReference fireNoPedidos = fireDados.getReference().child( "Pedidos" );
        fireNoPedidos.orderByChild("cliNome").addChildEventListener(childEventListener);

    }


    //Botão Comando - Ir Para Tela - Lista de Pendência de Entrega
    public void btnPendenciaAceite (View view) {
        Intent intent = new Intent( ListaPendEntregaTela.this, ListaPendAceiteTela.class );
        startActivity( intent );
    }

}
