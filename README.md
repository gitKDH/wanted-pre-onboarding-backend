# 📌프리온보딩 백엔드 인턴십 선발과제
***
## 📽️ 목차

[요구사항 분석](#요구사항-분석)

[API 명세](#-api명세)

[ERD](#-erd)

[유닛테스트](#유닛테스트)
***
## ❗️요구사항 분석
* 프로젝트 개요
  + 본 서비스는 기업의 채용을 위한 웹 서비스 입니다. 
  + 회사는 채용공고를 생성하고, 이에 사용자는 지원합니다.
* 요구사항
  + 채용공고를 등록합니다.
  + 채용공고를 수정합니다.
    + 포지션을 수정할 수 있습니다.
    + 보상금을 수정할 수 있습니다.
    + 채용 내용을 수정할 수 있습니다.
    + 사용 기술을 수정할 수 있습니다.
  + 채용공고를 삭제합니다.
  + 채용공고 목록을 가져옵니다.
  + 키워드를 이용해 채용공고를 검색합니다.
    + 회사 이름, 국가, 지역, 사용 기술을 검색하여 해당하는 공고를 찾습니다. 
  + 채용공고의 상세 내용을 가져옵니다.
    + 이 페이지에서는 해당 회사가 올린 다른 채용공고가 추가적으로 포함됩니다.
  + 사용자는 채용공고에 지원합니다.
    + 사용자는 한 공고에 1회 지원이 가능하며, 이미 지원한 공고라면 메시지를 반환합니다. 
* 필요 모델
  + 회사
  + 사용자
  + 채용공고
  + 지원내역
* 기술 환경
  + IDE : `IntelliJ`
  + 사용 언어 : `Java 11`
  + 프레임워크 : `Spring`
  + DB : `MySQL`
  + ORM : `Spring Data JPA`
***
## 📜 API명세
| 기능         | HTTP 메서드 | API PATH                   | Request                                                                                                                                   | Response                                                                                                                                                                                                                                                                                                                                                  | Description                                                                                                                    |
|------------|----------|----------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|
| 유저 생성      | POST     | /user                      | {<br/>"name":"김동현<br/>}                                                                                                                   | {<br/>"name":"김동현<br/>}                                                                                                                                                                                                                                                                                                                                   | 유저ID는 자동으로 생성됩니다.                                                                                                              |
| 회사 생성      | POST     | /company                   | {<br/>"name":"원티드",<br/>"nation":"한국",<br/>"region":"서울"<br/>}                                                                            | {<br/>"id":1,<br/>name":"원티드",<br/>"nation":"한국",<br/>"region":"서울"<br/>}                                                                                                                                                                                                                                                                                 | 회사ID는 자동으로 생성됩니다.                                                                                                   성          |
| 채용공고 등록    | POST     | /employment                | {<br/>"company_id": 1,<br/>"position": "백엔드 개발자",<br/>"reward": 50000,<br/>"detail": "우리 회사에서 백엔드 개발자를 채용합니다.",<br/>"skill": "Java"<br/>} | {<br/>"company_id": 1,<br/>"position": "백엔드 개발자",<br/>"reward": 50000,<br/>"detail": "우리 회사에서 백엔드 개발자를 채용합니다.",<br/>"skill": "Java"<br/>}                                                                                                                                                                                                                 | company_id를 입력하여 해당 회사의 공고로 등록합니다. 채용공고ID는 자동으로 생성됩니다.                                                                         |
| 채용공고 수정    | PUT      | /employment/{id}           | {<br/>"position": "백엔드 개발자",<br/>"reward": 50000,<br/>"detail": "우리 회사에서 백엔드 개발자를 채용합니다.",<br/>"skill": "python"<br/>}                    | {<br/>"position": "백엔드 개발자",<br/>"reward": 50000,<br/>"detail": "우리 회사에서 백엔드 개발자를 채용합니다.",<br/>"skill": "java"<br/>}                                                                                                                                                                                                                                      | 채용공고ID를 이용해 해당 공고의 내용을 수정할 수 있습니다.                                                                                             |
| 채용공고 삭제    | DELETE   | /employment/{id}           | -                                                                                                                                         | {<br/>"message": "채용공고 1번 삭제 완료."<br/>}                                                                                                                                                                                                                                                                                                                   | 채용공고ID를 이용해 해당 공고를 삭제할 수 있습니다.                                                                                                 |
| 채용공고 목록 조회 | GET      | /employment                | -                                                                                                                                         | <br/>{<br/>"id": 1,<br/>"companyName": "kakao",<br/>"nation": "korea",<br/>"region": "jeju",<br/>"position": "백엔드 시니어 개발자",<br/>"reward": 30000000,<br/>"skill": "java"<br/>},<br/>{<br/>"id": 2,<br/>"companyName": "LG",<br/>"nation": "korea",<br/>"region": "seoul",<br/>"position": "백엔드 시니어 개발자",<br/>"reward": 30000000,<br/>"skill": "java"<br/>} | 현재 등록된 채용공고의 전체 목록을 가져옵니다. 해당 응답에는 채용공고를 등록한 회사의 정보와 채용 정보가 표시됩니다.                                                             |
| 채용공고 검색    | GET      | /employment?search=keyward | -                                                                                                                                         | {<br/>"id": 11,<br/>"companyName": "kakao",<br/>"nation": "korea",<br/>"region": "seoul",<br/>"position": "백엔드 시니어 개발자",<br/>"reward": 30000000,<br/>"skill": "java<br/>"<br/>}                                                                                                                                                                           | url의 keyward부분에 원하는 검색어를 입력하면 해당 키워드와 일치하는 채용공고의 전체 내역을 가져옵니다. ex) /employment?search=seoul 입력 시, region이 seoul인 모든 채용공고를 가져옴. |
| 채용 상세 페이지  | GET      | /employment/{id}           | -                                                                                                                                         | {<br/>"id": 1,<br/>"companyName": "wanted",<br/>"nation": "korea",<br/>"region": "ansan",<br/>"position": "백엔드 시니어 개발자",<br/>"reward": 30000000,<br/>"skill": "python",<br/>"detail": "카카오에서 백엔드 시니어 개발자 팀장님을 채용합니다. 자격요건은...",<br/>"otherEmployment": [<br/>5,<br/>6<br/>]<br/>}                                                                         | 원하는 채용공고의 ID를 입력하면 해당 공고의 상세 페이지를 가져옵니다. 추가적으로 채용공고를 올린 회사의 다른 공고들의 리스트도 제공됩니다.                                                |
| 채용공고 지원    | POST     | /apply                     | {<br/>"userId" : 2,<br/>"employmentId" : 3<br/>}                                                                                          | {<br/>"userId" : 2,<br/>"employmentId" : 3<br/>}                                                                                                                                                                                                                                                                                                          | 회원ID와 채용공고ID를 입력하여 공고에 지원합니다. 유저는 한 공고당 한 번 지원이 가능하며, 이미 지원한 공고일 시에 "이미 지원한 공고입니다." 라는 문구를 출력합니다.                              |


***
## 🔗 ERD
<img width="531" alt="ERD" src="https://github.com/gitKDH/wanted-pre-onboarding-backend/assets/119571075/7ae125d4-3f1e-4b92-bfbe-b249c2599f35">

***
## 🔍유닛테스트

Junit과 mockito를 사용하여 총 8개의 Unit Test를 진행하였습니다.
<details>
<summary>유저 생성 테스트</summary>

```
package com.report.wantedpreonboardingbackend;

import com.report.wantedpreonboardingbackend.controller.UserController;
import com.report.wantedpreonboardingbackend.dto.UserDTO;
import com.report.wantedpreonboardingbackend.entity.User;
import com.report.wantedpreonboardingbackend.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserCreateUnitTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("김자바");

        Mockito.when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<UserDTO> response = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        UserDTO createdUser = response.getBody();
        assertNotNull(createdUser);
        assertEquals("김자바", createdUser.getName());
    }
}

```
</details>

<details>
<summary>회사 생성 테스트</summary>

```
package com.report.wantedpreonboardingbackend;

import com.report.wantedpreonboardingbackend.controller.CompanyController;
import com.report.wantedpreonboardingbackend.dto.CompanyDTO;
import com.report.wantedpreonboardingbackend.entity.Company;
import com.report.wantedpreonboardingbackend.service.CompanyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CompanyCreateUnitTest {
    @InjectMocks
    private CompanyController companyController;

    @Mock
    private CompanyService companyService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCompany() {
        Company company = new Company();
        company.setName("원티드");
        company.setNation("korea");
        company.setRegion("ansan");

        Mockito.when(companyService.createCompany(company)).thenReturn(company);

        ResponseEntity<CompanyDTO> response = companyController.createCompany(company);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        CompanyDTO createdCompany = response.getBody();
        assertNotNull(createdCompany);
        assertEquals("원티드", createdCompany.getName());
        assertEquals("korea", createdCompany.getNation());
        assertEquals("ansan", createdCompany.getRegion());
    }
}

```
</details>
<details>
<summary>채용공고 생성 테스트</summary>

```
package com.report.wantedpreonboardingbackend;

import com.report.wantedpreonboardingbackend.controller.EmploymentController;
import com.report.wantedpreonboardingbackend.dto.EmploymentDTO;
import com.report.wantedpreonboardingbackend.service.EmploymentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmploymentCreateUnitTest {
    @InjectMocks
    private EmploymentController employmentController;

    @Mock
    private EmploymentService employmentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateEmployment() {
        EmploymentDTO employmentDTO = new EmploymentDTO(1L, "백엔드", 50000, "백엔드 개발자를 채용합니다.", "java");

        Mockito.when(employmentService.createEmployment(employmentDTO)).thenReturn(employmentDTO);

        ResponseEntity<EmploymentDTO> response = employmentController.createEmployment(employmentDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        EmploymentDTO createdEmployment = response.getBody();
        assertNotNull(createdEmployment);
        assertEquals(1L, createdEmployment.getCompany_id().longValue());
        assertEquals("백엔드", createdEmployment.getPosition());
        assertEquals(50000, createdEmployment.getReward());
        assertEquals("백엔드 개발자를 채용합니다.", createdEmployment.getDetail());
        assertEquals("java", createdEmployment.getSkill());
    }
}
```
</details>
<details>
<summary>채용공고 수정 테스트</summary>

```
package com.report.wantedpreonboardingbackend;
import com.report.wantedpreonboardingbackend.controller.EmploymentController;
import com.report.wantedpreonboardingbackend.dto.EmploymentDTO;
import com.report.wantedpreonboardingbackend.entity.Employment;
import com.report.wantedpreonboardingbackend.service.EmploymentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmploymentUpdateUnitTest {
    @InjectMocks
    private EmploymentController employmentController;

    @Mock
    private EmploymentService employmentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateEmployment() {
        EmploymentDTO employmentDTO = new EmploymentDTO(1L, "Updated Position", 60000, "Updated Detail", "Updated Skill");

        Employment existingEmployment = new Employment("Sample Position", 50000, "Sample Detail", "Sample Skill", null);
        existingEmployment.setId(1L);

        Mockito.when(employmentService.findEmploymentById(1L)).thenReturn(existingEmployment);

        ResponseEntity<Map<String, Object>> response = employmentController.updateEmployment(1L, employmentDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Map<String, Object> updatedEmploymentResponse = response.getBody();
        assertNotNull(updatedEmploymentResponse);
        assertEquals("Updated Position", updatedEmploymentResponse.get("position"));
        assertEquals(60000, updatedEmploymentResponse.get("reward"));
        assertEquals("Updated Detail", updatedEmploymentResponse.get("detail"));
        assertEquals("Updated Skill", updatedEmploymentResponse.get("skill"));
    }
}
```
</details>
<details>
<summary>채용공고 삭제 테스트</summary>

```
package com.report.wantedpreonboardingbackend;
import com.report.wantedpreonboardingbackend.controller.EmploymentController;
import com.report.wantedpreonboardingbackend.service.EmploymentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmploymentDeleteUnitTest {
    @InjectMocks
    private EmploymentController employmentController;

    @Mock
    private EmploymentService employmentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteEmployment() {
        Long employmentId = 1L;

        Mockito.doNothing().when(employmentService).deleteEmployment(employmentId);

        ResponseEntity<?> response = employmentController.deleteEmployment(employmentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());
        assertEquals("채용공고 1번 삭제 완료.", ((Map<String, String>) response.getBody()).get("message"));
    }
}
```
</details>
<details>
<summary>채용공고 목록 조회 테스트</summary>

```
package com.report.wantedpreonboardingbackend;

import com.report.wantedpreonboardingbackend.controller.EmploymentController;
import com.report.wantedpreonboardingbackend.service.EmploymentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmploymentGetAllUnitTest {
    @InjectMocks
    private EmploymentController employmentController;

    @Mock
    private EmploymentService employmentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllEmployments() {
        Map<String, Object> employment1  = new HashMap<>();
        employment1.put("position", "Position 1");
        employment1.put("reward",1000);
        employment1.put("detail","Detail 1");
        employment1.put("skill","Skill 1");
        Map<String, Object> employment2  = new HashMap<>();
        employment2.put("position", "Position 2");
        employment2.put("reward",2000);
        employment2.put("detail","Detail 2");
        employment2.put("skill","Skill 2");
        List<Map<String, Object>> employmentList = new ArrayList<>();

        employmentList.add(employment1);
        employmentList.add(employment2);

        Mockito.when(employmentService.getAllEmployments()).thenReturn(employmentList);

        ResponseEntity<List<Map<String, Object>>> response = employmentController.getAllEmployments();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Map<String, Object>> returnedEmployments = response.getBody();
        assertNotNull(returnedEmployments);
        assertEquals(2, returnedEmployments.size());

        Map<String, Object> returnedEmployment1 = returnedEmployments.get(0);
        assertEquals("Position 1", returnedEmployment1.get("position"));
        assertEquals(Integer.valueOf(1000), returnedEmployment1.get("reward"));
        assertEquals("Detail 1", returnedEmployment1.get("detail"));
        assertEquals("Skill 1", returnedEmployment1.get("skill"));

        Map<String, Object> returnedEmployment2 = returnedEmployments.get(1);
        assertEquals("Position 2", returnedEmployment2.get("position"));
        assertEquals(Integer.valueOf(2000), returnedEmployment2.get("reward"));
        assertEquals("Detail 2", returnedEmployment2.get("detail"));
        assertEquals("Skill 2", returnedEmployment2.get("skill"));
    }
}

```
</details>
<details>
<summary>채용공고 상세 페이지 테스트</summary>

```
package com.report.wantedpreonboardingbackend;
import com.report.wantedpreonboardingbackend.controller.EmploymentController;
import com.report.wantedpreonboardingbackend.service.EmploymentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmploymentGetDetailsUnitTest {
    @InjectMocks
    private EmploymentController employmentController;

    @Mock
    private EmploymentService employmentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetEmploymentDetailsWithOtherOpenings() {
        Long employmentId = 1L;

        Map<String, Object> employmentDetails = new HashMap<>();
        employmentDetails.put("채용공고_id", employmentId);
        employmentDetails.put("회사명", "원티드랩");
        employmentDetails.put("국가", "한국");
        employmentDetails.put("지역", "서울");
        employmentDetails.put("채용포지션", "백엔드 주니어 개발자");
        employmentDetails.put("채용보상금", 1500000);
        employmentDetails.put("사용기술", "Python");
        employmentDetails.put("채용내용", "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..");

        List<Long> otherOpenings = new ArrayList<>();
        otherOpenings.add(2L);
        otherOpenings.add(3L);
        employmentDetails.put("회사가올린다른채용공고", otherOpenings);

        Mockito.when(employmentService.getEmploymentDetails(employmentId)).thenReturn(employmentDetails);

        ResponseEntity<Map<String, Object>> response = employmentController.getEmploymentDetails(employmentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Map<String, Object> returnedEmploymentDetails = response.getBody();
        assertNotNull(returnedEmploymentDetails);

        assertEquals(employmentId, returnedEmploymentDetails.get("채용공고_id"));
        assertEquals("원티드랩", returnedEmploymentDetails.get("회사명"));
        assertEquals("한국", returnedEmploymentDetails.get("국가"));
        assertEquals("서울", returnedEmploymentDetails.get("지역"));
        assertEquals("백엔드 주니어 개발자", returnedEmploymentDetails.get("채용포지션"));
        assertEquals(1500000, returnedEmploymentDetails.get("채용보상금"));
        assertEquals("Python", returnedEmploymentDetails.get("사용기술"));
        assertEquals("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..", returnedEmploymentDetails.get("채용내용"));

        List<Long> returnedOtherOpenings = (List<Long>) returnedEmploymentDetails.get("회사가올린다른채용공고");
        assertNotNull(returnedOtherOpenings);
        assertEquals(2, returnedOtherOpenings.size());
        assertEquals(2L, (long) returnedOtherOpenings.get(0));
        assertEquals(3L, (long) returnedOtherOpenings.get(1));
    }
}
```
</details>
<details>
<summary>채용공고 지원 테스트</summary>

```
package com.report.wantedpreonboardingbackend;

import com.report.wantedpreonboardingbackend.dto.ApplyDTO;
import com.report.wantedpreonboardingbackend.entity.Apply;
import com.report.wantedpreonboardingbackend.entity.Employment;
import com.report.wantedpreonboardingbackend.entity.User;
import com.report.wantedpreonboardingbackend.repository.ApplyRepository;
import com.report.wantedpreonboardingbackend.repository.EmploymentRepository;
import com.report.wantedpreonboardingbackend.repository.UserRepository;
import com.report.wantedpreonboardingbackend.service.ApplyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ApplyForJobUnitTest {
    @InjectMocks
    private ApplyService applyService;

    @Mock
    private ApplyRepository applyRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmploymentRepository employmentRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testApplyForJob() {
        User user = new User();
        user.setId(1L);

        Employment employment = new Employment();
        employment.setId(1L);

        ApplyDTO applyDTO = new ApplyDTO();
        applyDTO.setUserId(1L);
        applyDTO.setEmploymentId(1L);

        when(applyRepository.findByUserAndEmployment(user, employment)).thenReturn(Optional.empty());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        when(employmentRepository.findById(1L)).thenReturn(Optional.of(employment));

        when(applyRepository.save(any(Apply.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Apply apply = applyService.applyForJob(applyDTO);
        assert apply != null;

        when(applyRepository.findByUserAndEmployment(user, employment)).thenReturn(Optional.of(new Apply(user, employment)));

        assertThrows(RuntimeException.class, () -> applyService.applyForJob(applyDTO));
    }
}

```
</details>

***


