package io.github.zbrant.testesunitarios.matchers;

import io.github.zbrant.testesunitarios.utils.DataUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MatchersProprios {
    public static DiaSemanaMatcher caiEm(Integer diaSemana){
        return new DiaSemanaMatcher(diaSemana);
    }

    public static DiaSemanaMatcher caiNumaSegunda(){
        return new DiaSemanaMatcher(Calendar.MONDAY);
    }

    public static DataDiferencaDiasMatcher isHoje(){
        return new DataDiferencaDiasMatcher(0);
    }

    public static DataDiferencaDiasMatcher isHojeComDiferencaDias(Integer qtdDias){
        return new DataDiferencaDiasMatcher(qtdDias);
    }
}
