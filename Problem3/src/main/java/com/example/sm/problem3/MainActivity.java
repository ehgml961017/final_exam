package com.example.sm.problem3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //리스트만듦
        ArrayList<CustomerThread> list = new ArrayList<CustomerThread>();
        //매니저만듦
        Manager manager = new Manager();

        //
        for(int i = 0 ; i < 10 ; i++){
            Customer customer = new Customer("Customer" + i); //customer(숫자)라는 customer를 하나 만듦
            CustomerThread ct = new CustomerThread(customer); //customer를 이용한 스레드를 하나 만듦.
            list.add(ct);
            manager.add_customer(customer); //list안에 customer를 넣음 > manager가 list를 다룰 수 있는 존재!
            ct.start();
        }

//
//        for(CustomerThread ct : list){
//
//            try {
//                // need something here
//            } catch (InterruptedException e) { }
//        }

        manager.sort();

        //이건 리스트를 화면에 출력하는거 (아마도)
        MyBaseAdapter adapter = new MyBaseAdapter(this, manager.list);
        ListView listview = (ListView) findViewById(R.id.listView1) ;
        listview.setAdapter(adapter);


    }
}

class CustomerThread extends Thread{

    Customer customer;

    CustomerThread(Customer customer){
        this.customer = customer;
    }
    // need something here
    /*
    start를 하면 0~1000사이의 돈을 하나 리턴
    * */
    public void run()
    {
        for(int i  =0; i<10; i++) {
            customer.spend = 10;
            customer.work();

            try {
                sleep(0);
            } catch (InterruptedException e) {
                System.out.println("스레드에서 에러남 시발");
            }
        }
    }
    public String getCustomerName()
    {
        return customer.name;
    }
    public int getCustomerMoney()
    {
        return customer.spent_money;
    }
}

abstract class Person{

    static int money = 100000;
    int spent_money = 0;
    int spend;
    abstract void work();

}


class Customer extends Person{

    String name;
    Customer(String name){
        this.name = name;
    }

    @Override
    void work() {
        money -= spend;
        spent_money += spend;

    }

    // need something here
}


class Manager extends Person{
    ArrayList <Customer> list = new ArrayList<Customer>();

    void add_customer(Customer customer) {
        list.add(customer);
    }

    void sort(){ // 직접 소팅 알고리즘을 이용하여 코딩해야함. 자바 기본 정렬 메소드 이용시 감
        // need something here
        int i =0;
        while(i< list.size()-1)
        {
            if (list.get(i).spent_money > list.get(i+1).spent_money)
               {
                   list.add(i, list.get(i+1));
                   list.remove(i+2);
                   i=0;
               }
            else i++;
        }
    }

    @Override
    void work() {
        sort();
    }
}

// need something here

