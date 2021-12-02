package de.hfu;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.repository.ResidentRepositoryStub;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentService;
import de.hfu.residents.service.ResidentServiceException;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.easymock.EasyMock.*;

public class ResidentTest {
    @Test
    public void testFilteredResidentsListStub() {
        ResidentRepository stup = new ResidentRepositoryStub();

        ResidentService service = new BaseResidentService();
        service.setResidentRepository(stup);

        List<Resident> list1 = service.getFilteredResidentsList(new Resident("He*", null, null, null, null));
        assertThat(list1.get(0).getGivenName(), equalTo("Herman"));
        assertThat(list1.get(1).getGivenName(), equalTo("Helga"));

        List<Resident> list2 = service.getFilteredResidentsList(new Resident(null, null, null, "Dortmund", null));
        assertThat(list2.get(0).getGivenName(), equalTo("Herman"));
        assertThat(list2.get(1).getGivenName(), equalTo("Helga"));
        assertThat(list2.get(2).getGivenName(), equalTo("Otto"));

        List<Resident> list3 = service.getFilteredResidentsList(new Resident(null, "Winter", null, "Dortmund", null));
        assertThat(list3.get(0).getGivenName(), equalTo("Herman"));
        assertThat(list3.get(1).getGivenName(), equalTo("Helga"));
    }

    @Test
    public void testUniqueResidentStub() throws ResidentServiceException {
        ResidentRepository stup = new ResidentRepositoryStub();

        ResidentService service = new BaseResidentService();
        service.setResidentRepository(stup);

        try {
            service.getUniqueResident(new Resident("Herman", "*", null, null, null));
            fail();
        } catch (Exception e) { }
        try {
            service.getUniqueResident(new Resident(null, "Winter", null, null, null));
            fail();
        } catch (Exception e) { }

        Resident resident = service.getUniqueResident(new Resident("Otto", "Otto", null, null, null));
        assertThat(resident.getGivenName(), equalTo("Otto"));
    }

    @Test
    public void testFilteredResidentsListMock() {
        List<Resident> residentList = new ArrayList<>();
        residentList.add(new Resident("Herman", "Winter", "Hauptstrasse 1", "Dortmund", new Date(1950, 2, 21)));
        residentList.add(new Resident("Helga", "Winter", "Hauptstrasse 1", "Dortmund", new Date(1955, 3, 2)));
        residentList.add(new Resident("Dieter", "Morgen", "Steinstrasse 4", "Salzgitter", new Date(1929, 10, 14)));
        residentList.add(new Resident("Olga", "Polka", "Grosse Strasse 31", "Tübingen", new Date(1941, 2, 21)));
        residentList.add(new Resident("Otto", "Otto", "Weg 3", "Dortmund", new Date(2000, 5, 21)));

        ResidentRepository mock = createMock(ResidentRepository.class);
        expect(mock.getResidents()).andReturn(residentList);
        expect(mock.getResidents()).andReturn(residentList);
        expect(mock.getResidents()).andReturn(residentList);

        replay(mock);
        ResidentService service = new BaseResidentService();
        service.setResidentRepository(mock);

        List<Resident> list1 = service.getFilteredResidentsList(new Resident(null, null, "Hauptstrasse 1", null, new Date(1950, 2, 21)));
        assertThat(list1.get(0).getGivenName(), equalTo("Herman"));

        List<Resident> list2 = service.getFilteredResidentsList(new Resident(null, null, null, "Dortmund", null));
        assertThat(list2.get(0).getGivenName(), equalTo("Herman"));
        assertThat(list2.get(1).getGivenName(), equalTo("Helga"));
        assertThat(list2.get(2).getGivenName(), equalTo("Otto"));

        List<Resident> list3 = service.getFilteredResidentsList(new Resident(null, "Winter", null, "Dortmund", null));
        assertThat(list3.get(0).getGivenName(), equalTo("Herman"));
        assertThat(list3.get(1).getGivenName(), equalTo("Helga"));

        verify(mock);
    }
}
