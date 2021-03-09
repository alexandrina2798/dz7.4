package main.java;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(final String[] args) throws IllegalArgumentException {
        System.out.println("Ознакомьтесь с доступной базой стран");
        for (Country country : Country.values()) {
            System.out.println(country.toString());
        }

        System.out.println("*************************************************");
        System.out.println("Для быстрого поиска по базе введите название страны:");
        System.out.println();

        /*boolean repeat = true;          при необходимости повторить до победного
        while (repeat) { */
        Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
        String lostCountry = input.next();

        //Делаем первую букву в названии страны заглавной
        lostCountry = lostCountry.substring(0, 1).toUpperCase() + lostCountry.substring(1);

        try {
            Country foundedCountry = Country.valueOf(lostCountry);
            System.out.print("Страна найдена в базе: ");
            System.out.println(foundedCountry);
            if (foundedCountry.getIsOpen()) {
                System.out.println("Страна [" + foundedCountry + "] открыта для посещения.");
            } else {
                System.out.println("Страна [" + foundedCountry + "] закрыта для посещения.");
            }
            /*repeat = false;*/
        } catch (IllegalArgumentException e) {
            System.out.println("Наименование страны на английском введено некорректно, проверяем русское название");

            try {
                Country foundedCountry = Country.getByRuName(lostCountry);
                System.out.println(foundedCountry);
                if (foundedCountry.getIsOpen()) {
                    System.out.println("Страна [" + foundedCountry + "] открыта для посещения.");
                } else {
                    System.out.println("Страна [" + foundedCountry + "] закрыта для посещения.");
                }
                /*repeat = false;*/
            } catch (NoSuchCountryException noCountryError) {
                System.out.println("Проверьте написание страны");
                System.out.println();
            }

        } catch (NullPointerException e) {
            System.out.println("База пуста, либо мы не получили данных о стране. Обратитесь к администратору");
        }
        System.out.println("Были рады помочь, всего доброго");
    }


}
/*}*/

enum Country {
    Afghanistan("Афганистан"),
    Albania("Албания"),
    Algeria("Алжир"),
    Angola("Ангола"),
    Argentina("Аргентина"),
    Armenia("Армения"),
    Australia("Австралия"),
    Austria("Австрия"),
    Azerbaijan("Азербайджан"),
    Belarus("Беларусь"),
    Belgium("Бельгия"),
    Brazil("Бразилия"),
    Bulgaria("Болгария"),
    Cameroon("Камерун"),
    Canada("Канада"),
    Chile("Чили"),
    China("Китай"),
    Colombia("Колумбия"),
    Congo("Конго"),
    Croatia("Хорватия"),
    Cuba("Куба"),
    Cyprus("Кипр"),
    Denmark("Дания"),
    Ecuador("Эквадор"),
    Egypt("Египет"),
    Estonia("Эстония"),
    Ethiopia("Эфиопия"),
    Finland("Финляндия"),
    France("Франция"),
    Germany("Германия"),
    Ghana("Гана"),
    Gibraltar("Гибралтар"),
    Greece("Греция"),
    Greenland("Гренландия"),
    Guadeloupe("Гваделупа"),
    Guinea("Гвинея"),
    Honduras("Гондурас"),
    Hungary("Венгрия"),
    Iceland("Исландия"),
    India("Индия"),
    Indonesia("Индонезия"),
    Iran("Иран"),
    Iraq("Ирак"),
    Ireland("Ирландия"),
    Israel("Израиль"),
    Italy("Италия"),
    Jamaica("Ямайка"),
    Japan("Япония"),
    Kazakhstan("Казахстан"),
    Kenya("Кения"),
    Korea("Корея"),
    Latvia("Латвия"),
    Macedonia("Македония"),
    Madagascar("Мадагаскар"),
    Malaysia("Малайзия"),
    Malta("Мальта"),
    Mexico("Мексика"),
    Monaco("Монако"),
    Mongolia("Монголия"),
    Morocco("Морроко"),
    Mozambique("Мозамбик"),
    Nepal("Непал"),
    Netherlands("Голландия"),
    Nicaragua("Никарагуа"),
    Niger("Нигер"),
    Nigeria("Нигерия"),
    Norway("Норвегия"),
    Pakistan("Пакистан"),
    Panama("Панама"),
    Paraguay("Парагвай"),
    Peru("Перу"),
    Poland("Польша"),
    Portugal("Португалия"),
    Romania("Румыния"),
    Russia("Россия"),
    Senegal("Сенегал"),
    Serbia("Сербия"),
    Singapore("Сингапур"),
    Slovakia("Словакия"),
    Slovenia("Словения"),
    Spain("Испания"),
    Sweden("Швеция"),
    Switzerland("Швейцария"),
    Tajikistan("Таджикистан"),
    Tanzania("Танзания"),
    Thailand("Тайланд"),
    Turkey("Турция"),
    Turkmenistan("Туркменистан"),
    Uganda("Уганда"),
    Ukraine("Украина"),
    Uruguay("Уругвай"),
    Uzbekistan("Узбекистан"),
    Venezuela("Венесуэлла"),
    Vietnam("Вьетнам"),
    Yemen("Йемен"),
    Zambia("Замбия"),
    Zimbabwe("Зимбабве");

    private final String ruName;
    private final boolean isOpen;

    Country(final String ruName) {
        this.ruName = ruName;
        this.isOpen = this.isOpen();
    }

    private boolean isOpen() {
        Random random = new Random();
        return (random.nextInt() > random.nextInt());
    }

    public boolean getIsOpen() {
        return this.isOpen;
    }

    static Country getByRuName(final String ruName) throws NoSuchCountryException {
        boolean result = false;
        Country foundedCountry = null;
        for (Country country : Country.values()) {
            //скобки добавлены, чтобы не искались совпадения по части названия страны
            if (country.toString().contains("(" + ruName + ")")) {
                foundedCountry = country;
                result = true;
            }
        }

        if (result) {
            System.out.print("Страна найдена в базе: ");
            return foundedCountry;
        } else {
            throw new NoSuchCountryException(ruName);
        }
    }

    @Override
    public String toString() {
        return this.name() + " (" + ruName + ")";
    }

}

