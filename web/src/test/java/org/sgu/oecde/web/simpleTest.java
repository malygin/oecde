package org.sgu.oecde.web;

import java.util.HashSet;

import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author ShihovMY
 */
public class simpleTest{

    @Ignore
    @Test
    public void getTypes(){

        class a{
            int a=2;
            int b = 3;

            public int getB() {
                return b;
            }

            public void setB(int b) {
                this.b = b;
            }
        }
        class b extends a{
            int a=2;
            int b = 3;

            public int getB() {
                return b;
            }

            public void setB(int b) {
                this.b = b;
            }
        }
        a b = new b();
        b.a = 1;
        b.setB(2);
        System.out.println(b.a+"   "+((b)b).a);
        System.out.println(b.getB()+"   "+((b)b).getB());
    }
    
    @Ignore
    @Test
    public void test(){
        label:
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (i > 2)
                    break label;
                System.out.print(j);
            }
            System.out.print(" ");
        }
    }

//    @Ignore
    @Test
    public void asd(){
        HashSet<item>h = new HashSet<item>();
        h.add(new item(1));
        h.add(new item(2));
        System.out.println(h);
    }
}
class item{
    int id;

    public item(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final item other = (item) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}