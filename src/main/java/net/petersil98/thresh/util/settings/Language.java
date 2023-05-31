package net.petersil98.thresh.util.settings;

public enum Language {
    AR_AE("ar_AE"),
    CS_CZ("cs_CZ"),
    DE_DE("de_DE"),
    EL_GR("el_GR"),
    EN_AU("en_AU"),
    EN_GB("en_GB"),
    EN_PH("en_PH"),
    EN_SG("en_SG"),
    EN_US("en_US"),
    ES_AR("es_AR"),
    ES_ES("es_ES"),
    ES_MX("es_MX"),
    FR_FR("fr_FR"),
    HU_HU("hu_HU"),
    IT_IT("it_IT"),
    JA_JP("ja_JP"),
    KO_KR("ko_KR"),
    PL_PL("pl_PL"),
    PT_BR("pt_BR"),
    RO_RO("ro_RO"),
    RU_RU("ru_RU"),
    TH_TH("th_TH"),
    TR_TR("tr_TR"),
    VI_VN("vi_VN"),
    ZH_CN("zh_CN"),
    ZH_MY("zh_MY"),
    ZH_TW("zh_TW");

    final String name;
    Language(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
