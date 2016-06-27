package com.thoughtworks.pos.domains;

import com.thoughtworks.pos.common.EmptyShoppingCartException;
import com.thoughtworks.pos.services.services.ReportDataGenerator;

/**
 * Created by Administrator on 2014/12/28.
 */
public class Pos {
    public String getShoppingList(ShoppingChart shoppingChart) throws EmptyShoppingCartException {

        Report report = new ReportDataGenerator(shoppingChart).generate();
        boolean needPromote=false;
        StringBuilder shoppingListBuilder = new StringBuilder()
                .append("***�̵깺���嵥***\n");

        for (ItemGroup itemGroup : report.getItemGroupies()) {
            if(!needPromote&&itemGroup.GroupIsPromotion()&&itemGroup.groupSize()>1)
                needPromote=true;
            shoppingListBuilder.append(

                    new StringBuilder()
                            .append("���ƣ�").append(itemGroup.groupName()).append("��")
                            .append("������").append(itemGroup.groupSize()).append(itemGroup.groupUnit()).append("��")
                            .append("���ۣ�").append(String.format("%.2f", itemGroup.groupPrice())).append("(Ԫ)").append("��")
                            .append("С�ƣ�").append(String.format("%.2f", itemGroup.subTotal())).append("(Ԫ)").append("\n")
                            .toString());
        }

        if(needPromote){
            shoppingListBuilder
                    .append("----------------------\n")
                    .append("����������Ʒ��\n");
            for (ItemGroup itemGroup : report.getItemGroupies()) {
            if(itemGroup.GroupIsPromotion()){
                shoppingListBuilder.append(
                        new StringBuilder()
                                .append("���ƣ�").append(itemGroup.groupName()).append("��")
                                .append("������").append("1").append(itemGroup.groupUnit()).append("\n")
                                .toString());
            }

            }}

        StringBuilder subStringBuilder = shoppingListBuilder
                .append("----------------------\n")
                .append("�ܼƣ�").append(String.format("%.2f", report.getTotal())).append("(Ԫ)").append("\n");

        double saving = report.getSaving();
        if (saving == 0) {
            return subStringBuilder
                    .append("**********************\n")
                    .toString();
        }
        return subStringBuilder
                .append("��ʡ��").append(String.format("%.2f", saving)).append("(Ԫ)").append("\n")
                .append("**********************\n")
                .toString();
    }
}