package mx.tec.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;




@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UsingMocksApplicationTests {
	
	@Mock
	List<String> direwolvesList;
	
	@Spy
	List<String> theWallCastlesList = new ArrayList<String>();
	
	@Mock
	List<String> swordsList;	
	
	@Captor
	ArgumentCaptor<String> swordName;
	
//	@Mock
//	Map<String, String> translationMap;
	 
//	@Mock
//	DothrakiTranslator translator;
	
	



	
	@Test
	public void whenUseMockAnnotation_thenMockIsInjected() {
		direwolvesList.add("drogon");
		verify(direwolvesList).add(anyString());
		assertEquals(0, direwolvesList.size());

		when(direwolvesList.size()).thenReturn(100);
		assertEquals(100, direwolvesList.size());
	}
	
	@Test
	public void whenUseSpyAnnotation_thenSpyIsInjected() {
	    theWallCastlesList.add("Castle Black");
	    theWallCastlesList.add("Eastwatch");
	 
	    verify(theWallCastlesList).add("Castle Black");
	    verify(theWallCastlesList).add("Eastwatch");
	 
	    assertEquals(2, theWallCastlesList.size());
	 
	    doReturn(100).when(theWallCastlesList).size();
	    assertEquals(100, theWallCastlesList.size());
	}
	
	@Test
	public void whenUseCaptorAnnotation_thenTheSame() {
		swordsList.add("needle");
	    verify(swordsList).add(swordName.capture());
	 
	    assertEquals("needle", swordName.getValue());
	}
	
	@Spy
	DothrakiTranslator translator = new DothrakiTranslator();
	
	@Captor
	ArgumentCaptor<String> translation;
	
	@Captor
	ArgumentCaptor<String> original;
	
	private static final Log LOG = LogFactory.getLog(UsingMocksApplicationTests.class);
	
	@Test
	public void whenUseInjectMocksAnnotation_thenCorrect() {
	   // when(translationMap.get("khaleesi")).thenReturn("queen");
		
		translator.addTranslation("khaleesi", "queen");
		//when(translator.getTranslation("khaleesi")).thenReturn("queen");
		verify(translator).addTranslation(original.capture(), translation.capture());
		LOG.info("Message to translate " + original.getValue() + " translation: " + translation.getValue());
	    assertEquals("queen", translator.getTranslation("khaleesi"));
	}





}
