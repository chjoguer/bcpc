package com.bcpc.bffcorebank.service.FacadeCore;

import com.bcpc.bffcorebank.domain.Account;

public interface IFacede {
     int inactiveClient();
     Account processCreateAccount(Account account);
}
