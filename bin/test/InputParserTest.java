����   4 �  test/InputParserTest  java/lang/Object 	indexFile Ljava/io/File; 	itemsFile <init> ()V Code
    	 LineNumberTable LocalVariableTable this Ltest/InputParserTest; setUp 
Exceptions  java/lang/Exception RuntimeVisibleAnnotations Lorg/junit/Before;  java/io/File  fixtures/sampleIndexes2.json
     (Ljava/lang/String;)V	     ! fixtures/sampleItems2.json	  #   testParseJsonFileToItems Lorg/junit/Test; ' java/lang/StringBuilder
 &  * {

 & , - . append -(Ljava/lang/String;)Ljava/lang/StringBuilder; 0 'ITEM000004':{
 2 "name": '电池',
 4 "unit": '个',
 6 "price": 2.00,
 8 "discount": 0.8
 : }

 & < = > toString ()Ljava/lang/String;
  @ A B WriteToFile #(Ljava/io/File;Ljava/lang/String;)V D [
 F "ITEM000004" H ] J 2com/thoughtworks/pos/services/services/InputParser
 I L  M (Ljava/io/File;Ljava/io/File;)V
 I O P Q parser .()Lcom/thoughtworks/pos/domains/ShoppingChart;
 S U T *com/thoughtworks/pos/domains/ShoppingChart V W getItems ()Ljava/util/ArrayList;
 Y [ Z java/util/ArrayList \ ] size ()I
 _ a ` java/lang/Integer b c valueOf (I)Ljava/lang/Integer;
 e g f org/hamcrest/CoreMatchers h i is *(Ljava/lang/Object;)Lorg/hamcrest/Matcher;
 k m l org/junit/Assert n o 
assertThat +(Ljava/lang/Object;Lorg/hamcrest/Matcher;)V
 Y q r s get (I)Ljava/lang/Object; u !com/thoughtworks/pos/domains/Item
 t w x > getName z 电池
 t | } > 
getBarcode  
ITEM000004
 t � � > getUnit � 个
 t � � � getPrice ()D
 � � � java/lang/Double b � (D)Ljava/lang/Double;@       
 t � � � getDiscount?陙���� sampleIndex Ljava/lang/String; sampleItems inputParser 4Lcom/thoughtworks/pos/services/services/InputParser; items Ljava/util/ArrayList; item #Lcom/thoughtworks/pos/domains/Item; LocalVariableTypeTable :Ljava/util/ArrayList<Lcom/thoughtworks/pos/domains/Item;>; � java/io/FileNotFoundException � java/io/PrintWriter
 � �  � (Ljava/io/File;)V
 � � �  write
 � � � 	 close file content printWriter Ljava/io/PrintWriter; testParseJsonWhenHasNoDiscount � "price": 2.00
 
SourceFile InputParserTest.java !                   	  
   /     *� �                          	                
   M     *� Y� � *� Y � � "�                             $ 	             %   
  �     � &Y� ()� +/� +1� +3� +5� +7� +9� +9� +� ;L**� +� ?� &Y� (C� +E� +G� +� ;M**� ",� ?� IY*� *� "� KN-� N� R:� X� ^� ^� d� j� p� t:� vy� d� j� {~� d� j� ��� d� j� �� � �� �� d� j� �� � �� �� d� j�       v       !  "  #  $  %   & % ' * ( / ) 2   3 * < , C - H . M / R 0 U , V 1 _ 3 o 4 x 6 � 7 � 8 � 9 � : � ; � < � =    >    �     3 � � �  V � � �  o v � �  x m � �  � P � �  �     x m � �   A B       � 
   g     � �Y+� �N-,� �-� ��           @ 	 A  B  C    *            �      � �  	 
 � �   � 	             %   
  W     �� &Y� ()� +/� +1� +3� +�� +9� +9� +� ;L**� +� ?� &Y� (C� +E� +G� +� ;M**� ",� ?� IY*� *� "� KN-� N� R:� p� t:� �� �� �� d� j�       ^    G  H  I  J  K  L   M % N * O - G . P 7 R > S C T H U M V P R Q W Z Y j Z s [ ~ \ � ]    >    �     . c � �  Q @ � �  j ' � �  s  � �  ~  � �  �     s  � �   �    �