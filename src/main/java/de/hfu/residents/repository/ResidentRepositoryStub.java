package de.hfu.residents.repository;

import de.hfu.residents.domain.Resident;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResidentRepositoryStub implements ResidentRepository {

    private final List<Resident> residentList;

    public ResidentRepositoryStub() {
        residentList = new ArrayList<>();
        residentList.add(new Resident("Herman", "Winter", "Hauptstrasse 1", "Dortmund", new Date(1950, 2, 21)));
        residentList.add(new Resident("Helga", "Winter", "Hauptstrasse 1", "Dortmund", new Date(1955, 3, 2)));
        residentList.add(new Resident("Dieter", "Morgen", "Steinstrasse 4", "Salzgitter", new Date(1929, 10, 14)));
        residentList.add(new Resident("Olga", "Polka", "Grosse Strasse 31", "Tübingen", new Date(1941, 2, 21)));
        residentList.add(new Resident("Otto", "Otto", "Weg 3", "Dortmund", new Date(2000, 5, 21)));
    }

    @Override
    public List<Resident> getResidents() {
        return residentList;
    }
}
