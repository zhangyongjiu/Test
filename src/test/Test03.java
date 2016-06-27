package test;
import com.thoughtworks.pos.common.EmptyShoppingCartException;
import com.thoughtworks.pos.domains.Item;
import com.thoughtworks.pos.domains.Pos;
import com.thoughtworks.pos.domains.ShoppingChart;
import com.thoughtworks.pos.bin.try0;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
/**
 * Created by pc on 2016/6/25.
 */
public class Test03 {
	   
    @Test
    public void testShouldSupportDiscountWhenHavingOneFavourableItem() throws EmptyShoppingCartException {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000001", "ѩ��", "ƿ", 2.00, 0.8));

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        // then
        String expectedShoppingList =
                "***�̵깺���嵥***\n"
                        + "���ƣ�ѩ�̣�������1ƿ�����ۣ�2.00(Ԫ)��С�ƣ�1.60(Ԫ)\n"
                        + "----------------------\n"
                        + "�ܼƣ�1.60(Ԫ)\n"
                        + "��ʡ��0.40(Ԫ)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testShouldSupportDiscountWhenHavingTwoFavourableItem() throws EmptyShoppingCartException {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000001", "ѩ��", "ƿ", 2.00, 0.8));
        shoppingChart.add(new Item("ITEM000001", "ѩ��", "ƿ", 2.00, 0.8));

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        // then
        String expectedShoppingList =
                "***�̵깺���嵥***\n"
                        + "���ƣ�ѩ�̣�������2ƿ�����ۣ�2.00(Ԫ)��С�ƣ�3.20(Ԫ)\n"
                        + "----------------------\n"
                        + "�ܼƣ�3.20(Ԫ)\n"
                        + "��ʡ��0.80(Ԫ)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testShouldSupportDiscountWhenHavingMultipleItem() throws EmptyShoppingCartException {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000001", "ѩ��", "ƿ", 2.00, 0.8));
        shoppingChart.add(new Item("ITEM000001", "ѩ��", "ƿ", 2.00, 0.8));
        shoppingChart.add(new Item("ITEM000000", "�ɿڿ���", "ƿ", 3.00,0.8));
        shoppingChart.add(new Item("ITEM000004","���","��",3.00,0.7));
        shoppingChart.add(new Item("ITEM000004","���","��",3.00,0.7));
        shoppingChart.add(new Item("ITEM000004","���","��",3.00,0.7));

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        // then
        String expectedShoppingList =
                "***�̵깺���嵥***\n"
                        + "���ƣ�ѩ�̣�������2ƿ�����ۣ�2.00(Ԫ)��С�ƣ�3.20(Ԫ)\n"
                        + "���ƣ��ɿڿ��֣�������1ƿ�����ۣ�3.00(Ԫ)��С�ƣ�2.40(Ԫ)\n"
                        + "���ƣ���أ�������3�������ۣ�3.00(Ԫ)��С�ƣ�6.30(Ԫ)\n"
                        + "----------------------\n"
                        + "�ܼƣ�11.90(Ԫ)\n"
                        + "��ʡ��4.10(Ԫ)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
}
