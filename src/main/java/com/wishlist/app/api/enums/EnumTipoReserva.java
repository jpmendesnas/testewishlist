package com.wishlist.app.api.enums;

public enum EnumTipoReserva {

    INTEGRAL(0,"Integral"),
    PRO_RATA(1,"Pro Rata"),
    POR_DEMANDA(2,"Por Demanda");

    private Long id;

    private String nome;


    EnumTipoReserva(Integer id, String nome) {
        this.id = Long.valueOf(id);
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public static EnumTipoReserva valueOf(Long id) {
        for (EnumTipoReserva e : EnumTipoReserva.values()) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

}
