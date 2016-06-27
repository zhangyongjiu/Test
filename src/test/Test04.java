package test;

import com.thoughtworks.pos.common.EmptyShoppingCartException;
import com.thoughtworks.pos.domains.Item;
import com.thoughtworks.pos.domains.Pos;
import com.thoughtworks.pos.domains.ShoppingChart;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.After;
import org.junit.Before;
/**
 * Created by pc on 2016/6/25.
 */
public class Test04 {
	
	
	
	 private File indexFile;
	    private File itemsFile;


	    @Before
	    public void setUp() throws Exception {
	        indexFile = new File("./sampleIndex.json");
	        itemsFile = new File("./itemsFile.json");

	    }

	    @After
	    public void tearDown() throws Exception {
	        if(indexFile.exists()){
	            indexFile.delete();
	        }
	        if(itemsFile.exists()){
	            itemsFile.delete();
	        }

	    }
	 @Test
	public void testShouldSupportDiscountRatherThanPromotionWhenHavingOneItemWithPromotionSign() throws EmptyShoppingCartException {
        // given

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "�ɿڿ���", "ƿ", 3.00,0.80));

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        // then
        String expectedShoppingList =
                "***�̵깺���嵥***\n"
                        + "���ƣ��ɿڿ��֣�������1ƿ�����ۣ�3.00(Ԫ)��С�ƣ�2.40(Ԫ)\n"
                        + "----------------------\n"
                        + "�ܼƣ�2.40(Ԫ)\n"
                        + "��ʡ��0.60(Ԫ)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testShouldSupportPromotionWhenHavingTwoItemWithOutDiscount() throws EmptyShoppingCartException {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "�ɿڿ���", "ƿ", 3.00,true));
        shoppingChart.add(new Item("ITEM000000", "�ɿڿ���", "ƿ", 3.00,true));
        shoppingChart.add(new Item("ITEM000000", "�ɿڿ���", "ƿ", 3.00,true));
        shoppingChart.add(new Item("ITEM000001", "ѩ��", "ƿ", 3.00,0.8));
        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        // then
        String expectedShoppingList =
                "***�̵깺���嵥***\n"
                        + "���ƣ��ɿڿ��֣�������3ƿ�����ۣ�3.00(Ԫ)��С�ƣ�6.00(Ԫ)\n"
                		+ "���ƣ�ѩ�̣�������1ƿ�����ۣ�3.00(Ԫ)��С�ƣ�2.40(Ԫ)\n"
                        + "----------------------\n"
                        + "����������Ʒ��\n"
                        + "���ƣ��ɿڿ��֣�������1ƿ\n"
                        +"----------------------\n"
                        + "�ܼƣ�8.40(Ԫ)\n"
                        + "��ʡ��0.60(Ԫ)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testShouldRecognizeDiscountOrPromotionWhenHavingDifferentTypesOfItems() throws EmptyShoppingCartException {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000001", "ѩ��", "ƿ", 2.00, true));
        shoppingChart.add(new Item("ITEM000001", "ѩ��", "ƿ", 2.00, true));
        shoppingChart.add(new Item("ITEM000001", "ѩ��", "ƿ", 2.00, true));
        shoppingChart.add(new Item("ITEM000000", "�ɿڿ���", "ƿ", 3.00,true));
        shoppingChart.add(new Item("ITEM000000", "�ɿڿ���", "ƿ", 3.00,true));
        shoppingChart.add(new Item("ITEM000000", "�ɿڿ���", "ƿ", 3.00,true));
        shoppingChart.add(new Item("ITEM000004","���","��",3.00,0.8));



        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        // then
        String expectedShoppingList =
                "***�̵깺���嵥***\n"
                        + "���ƣ�ѩ�̣�������3ƿ�����ۣ�2.00(Ԫ)��С�ƣ�4.00(Ԫ)\n"
                        + "���ƣ��ɿڿ��֣�������3ƿ�����ۣ�3.00(Ԫ)��С�ƣ�6.00(Ԫ)\n"
                        + "���ƣ���أ�������1�������ۣ�3.00(Ԫ)��С�ƣ�2.40(Ԫ)\n"
                        + "----------------------\n"
                        + "����������Ʒ��\n"
                        + "���ƣ�ѩ�̣�������1ƿ\n"
                        + "���ƣ��ɿڿ��֣�������1ƿ\n"
                        +"----------------------\n"
                        + "�ܼƣ�12.40(Ԫ)\n"
                        + "��ʡ��0.60(Ԫ)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
}
