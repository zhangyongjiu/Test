package test;

import com.thoughtworks.pos.common.EmptyShoppingCartException;
import com.thoughtworks.pos.domains.Item;
import com.thoughtworks.pos.domains.Pos;
import com.thoughtworks.pos.domains.ShoppingChart;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2014/12/28.
 */
public class PosTest {
    @Test
    public void testGetCorrectShoppingListForSingleItem() throws Exception {
        // given
        Item cokeCola = new Item("ITEM000000", "�ɿڿ���", "ƿ", 3.00);
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(cokeCola);

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        // then
        String expectedShoppingList =
                          "***�̵깺���嵥***\n"
                        + "���ƣ��ɿڿ��֣�������1ƿ�����ۣ�3.00(Ԫ)��С�ƣ�3.00(Ԫ)\n"
                        + "----------------------\n"
                        + "�ܼƣ�3.00(Ԫ)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

    @Test
    public void testGetCorrectShoppingListForTwoSameItems() throws Exception {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "�ɿڿ���", "ƿ", 3.00));
        shoppingChart.add(new Item("ITEM000000", "�ɿڿ���", "ƿ", 3.00));

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        // then
        String expectedShoppingList =
                          "***�̵깺���嵥***\n"
                        + "���ƣ��ɿڿ��֣�������2ƿ�����ۣ�3.00(Ԫ)��С�ƣ�6.00(Ԫ)\n"
                        + "----------------------\n"
                        + "�ܼƣ�6.00(Ԫ)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

    @Test
    public void testGetCorrectShoppingListForMultipleItemsWithMultipleTypes() throws Exception{
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "ѩ��", "ƿ", 2.00));
        shoppingChart.add(new Item("ITEM000001", "�ɿڿ���", "ƿ", 3.00));

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        // then
        String expectedShoppingList =
                "***�̵깺���嵥***\n"
                        + "���ƣ�ѩ�̣�������1ƿ�����ۣ�2.00(Ԫ)��С�ƣ�2.00(Ԫ)\n"
                        + "���ƣ��ɿڿ��֣�������1ƿ�����ۣ�3.00(Ԫ)��С�ƣ�3.00(Ԫ)\n"
                        + "----------------------\n"
                        + "�ܼƣ�5.00(Ԫ)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

    @Test
    public void testGetCorrectShoppingListWhenDifferentItemHaveSameItemName() throws  Exception{
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "ѩ��", "ƿ", 2.00));
        shoppingChart.add(new Item("ITEM000002", "ѩ��", "ƿ", 3.00));

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        // then
        String expectedShoppingList =
                "***�̵깺���嵥***\n"
                        + "���ƣ�ѩ�̣�������1ƿ�����ۣ�2.00(Ԫ)��С�ƣ�2.00(Ԫ)\n"
                        + "���ƣ�ѩ�̣�������1ƿ�����ۣ�3.00(Ԫ)��С�ƣ�3.00(Ԫ)\n"
                        + "----------------------\n"
                        + "�ܼƣ�5.00(Ԫ)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

    @Test(expected = EmptyShoppingCartException.class)
    public void testThrowExceptionWhenNoItemsInShoppingCart() throws EmptyShoppingCartException{
        // given
        ShoppingChart shoppingChart = new ShoppingChart();

        // when
        Pos pos = new Pos();
        pos.getShoppingList(shoppingChart);
    }

    @Test
    public void testShouldSupportDiscountWhenHavingOneFavourableItem() throws EmptyShoppingCartException {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "ѩ��", "ƿ", 2.00, 0.8));

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
}
