package concerttours.events;

import de.hybris.platform.servicelayer.event.events.AbstractEvent;

public class BandAlbumSalesEvent extends AbstractEvent {
    private String code;
    private String name;
    private Long albumSales;

    public BandAlbumSalesEvent(String code, String name, Long albumSales) {
        this.code = code;
        this.name = name;
        this.albumSales = albumSales;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAlbumSales() {
        return albumSales;
    }

    public void setAlbumSales(Long albumSales) {
        this.albumSales = albumSales;
    }
}
