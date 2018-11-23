package br.com.gasemanueladm.detalhePedido;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.gasemanueladm.R;
import br.com.gasemanueladm.listaPendAceite.ListaPendAceiteDados;
import br.com.gasemanueladm.listaPendAceite.ListaPendAceiteTela;

public class DetalhePedidoTela extends AppCompatActivity {

    //Declarar Objetos e Classes
    EditText            dataPed, horaPed, quantProd, valorUnit, valorTotal, observacoes, statusPed;
    DetalhePedidoDados  detalhePedidoDados;

    private FirebaseDatabase    fireDados = FirebaseDatabase.getInstance();  //Firebase - Biblioteca Banco de Dados
    private FirebaseAuth        firebaseAuth = FirebaseAuth.getInstance();

    private ListaPendAceiteDados listaPendAceiteDados;

    @Override protected void onCreate(Bundle savedInstanceState){super.onCreate(savedInstanceState);setContentView(R.layout.detalhe_pedido );
        //Declarar Objetos
        dataPed     = findViewById( R.id.dataXml );
        horaPed     = findViewById( R.id.horaXml );
        quantProd   = findViewById( R.id.quantXml );
        valorUnit   = findViewById( R.id.valorUnitXml );
        valorTotal  = findViewById( R.id.valorTotalXml );
        observacoes = findViewById( R.id.obsPedidoXml );
        statusPed   = findViewById( R.id.statusPedidoXml );

        //Receber os dados e Colocar nos Campos                                                            Recebe da Tela Anterior !!!!!
        listaPendAceiteDados = (ListaPendAceiteDados) getIntent().getSerializableExtra( "Item" );

        //Colocar Valores nos Campos
        dataPed.setText( listaPendAceiteDados.getPedData() );
        horaPed.setText( listaPendAceiteDados.getPedHora());
        quantProd.setText( listaPendAceiteDados.getProdGasQuant() );
        valorUnit.setText( listaPendAceiteDados.getProdGasValorUnit() );
        valorTotal.setText( listaPendAceiteDados.getPedValorTotal() );
        observacoes.setText( listaPendAceiteDados.getPedObserv() );
        statusPed.setText( listaPendAceiteDados.getPedStatus());

    }

    public void btnAceitarPedido (View view) {

        //O Que Quer Mudar
         DatabaseReference dadosPedidos =  fireDados.getReference( "Pedidos" ).child( listaPendAceiteDados.getPedIdCliente() );
        //O Que Mudar
         dadosPedidos.child("PedStatus").setValue( "Aceito");

        //Como fazer para notificar o cliente ?!?!?!?
        //como fazer para já atualizar a alista ?!?!?!?!?!

        Intent intent = new Intent(this, ListaPendAceiteTela.class);
        startActivity( intent );







  //      Toast.makeText( this, detalhePedidoDados.getNome()  ,Toast.LENGTH_LONG ).show();

//        DatabaseReference tbPedidos =  PedidoAdmApplication.getFireBaseData().getReference("Pedidos").child(PedidoAdmApplication.getFireBaseAuth().getCurrentUser().getUid());
//        tbPedidos.child("StatusPed").setValue("Aceito");

    }

}

//receber os DetalhePedidoDados
//classe DetalhePedidoDados com implements
