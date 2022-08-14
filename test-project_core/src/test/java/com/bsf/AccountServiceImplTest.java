package com.bsf;

import com.bsf.builder.AccountBuilder;
import com.bsf.constant.AccountConstant;
import com.bsf.constant.MasterConstant;
import com.bsf.dto.Account;
import com.bsf.dto.RequestWrapperDTO;
import com.bsf.entity.AccountEntity;
import com.bsf.mapper.AccountMapper;
import com.bsf.repository.AccountRepository;
import com.bsf.service.impl.AccountServiceImpl;
import com.bsf.utils.AssertionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = AccountServiceImplTest.class)
@RunWith(SpringRunner.class)
public class AccountServiceImplTest {

    @Autowired
    AccountServiceImpl accountService;

    @MockBean
    AccountRepository accountRepository;

    private AccountEntity accountEntity;

    private Account account;

    @BeforeEach
    void before(){
        accountEntity = new AccountEntity();
        accountEntity.setAccountId("ACC01");
        accountEntity.setFullName("Ghadeer");
        accountEntity.setEmailAddress("ghadeerof@gmail.com");
        accountEntity.setAccountBalance(BigDecimal.valueOf(50000));
        accountEntity.setCreatedAt(new Date());

        account.setFullName("Salam");
        account.setEmailAddress("salam@gmail.com");
        account.setAccountBalance(BigDecimal.valueOf(20000));
        account.setCreatedAt(new Date());
    }

    @Test
    void givenAddAccount_whenValidAccount_thenSuccess(){
        Mockito.when(accountRepository.save(Mockito.any())).thenReturn(accountEntity);
        RequestWrapperDTO requestWrapperDTO = new RequestWrapperDTO();
        Map<String, AccountBuilder> expectedResponse = new HashMap<>();
        AccountBuilder expectedAccountBuilder = AccountMapper.mapToAccountBuilder(accountEntity);
        expectedResponse.put(MasterConstant.MSG_FIELD, expectedAccountBuilder);
        requestWrapperDTO.setRequest(account);
        requestWrapperDTO.setResponse(accountEntity);

        RequestWrapperDTO responseWrapperDTO = accountService.addAccount(account);

        Map<String, AccountBuilder> actualResponse = (Map<String, AccountBuilder>) responseWrapperDTO.getResponse();
        AccountBuilder actualAccountBuilder = actualResponse.get(MasterConstant.MSG_FIELD);

        AssertionUtils.assertAccount(expectedAccountBuilder, actualAccountBuilder);
    }

}

