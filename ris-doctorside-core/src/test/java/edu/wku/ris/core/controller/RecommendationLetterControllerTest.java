package edu.wku.ris.core.controller;

import edu.wku.ris.common.pojo.exception.MyAssert;
import edu.wku.ris.common.pojo.response.ResponseVO;
import edu.wku.ris.core.pojo.entity.RecommendationLetterForm;
import edu.wku.ris.core.service.RecommendationLetterService;
import edu.wku.ris.core.service.impl.RecommendationLetterServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/5/3 12:52
 */
@SpringBootTest
public class RecommendationLetterControllerTest {

    @Spy
    @InjectMocks
    public RecommendationLetterController recommendationLetterController;

    @Mock
    public RecommendationLetterServiceImpl recommendationLetterServiceImpl;

    @BeforeEach
    public void openMock(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testSubmitRecommendationLetterApplication(){
        RecommendationLetterForm recommendationLetterForm = new RecommendationLetterForm();
        recommendationLetterForm.setId(123l);
        recommendationLetterForm.setDoctorId(001l);
        Mockito.when(recommendationLetterController.test()).thenReturn("world");
        Assertions.assertNotNull(recommendationLetterController.test());

        recommendationLetterController.submitRecommendationLetterApplication(recommendationLetterForm);
        Mockito.verify(recommendationLetterServiceImpl).submitLetterForm(recommendationLetterForm);
        Mockito.doNothing().when(recommendationLetterServiceImpl).submitLetterForm(recommendationLetterForm);
    }
}
