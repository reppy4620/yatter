package com.dmm.bootcamp.yatter2023.di

import com.dmm.bootcamp.yatter2023.domain.repository.AccountRepository
import com.dmm.bootcamp.yatter2023.domain.repository.StatusRepository
import com.dmm.bootcamp.yatter2023.domain.service.CheckLoginService
import com.dmm.bootcamp.yatter2023.domain.service.GetMeService
import com.dmm.bootcamp.yatter2023.domain.service.LoginService
import com.dmm.bootcamp.yatter2023.domain.service.LogoutService
import com.dmm.bootcamp.yatter2023.infra.domain.repository.AccountRepositoryImpl
import com.dmm.bootcamp.yatter2023.infra.domain.repository.StatusRepositoryImpl
import com.dmm.bootcamp.yatter2023.infra.domain.service.CheckLoginServiceImpl
import com.dmm.bootcamp.yatter2023.infra.domain.service.GetMeServiceImpl
import com.dmm.bootcamp.yatter2023.infra.domain.service.LoginServiceImpl
import com.dmm.bootcamp.yatter2023.infra.domain.service.LogoutServiceImpl
import org.koin.dsl.module

internal val domainImplModule = module {
  single<AccountRepository> { AccountRepositoryImpl(get(), get(), get()) }
  single<StatusRepository> { StatusRepositoryImpl(get(), get()) }

  factory<CheckLoginService> { CheckLoginServiceImpl(get()) }
  factory<GetMeService> { GetMeServiceImpl(get(), get()) }
  factory<LoginService> { LoginServiceImpl(get()) }

  factory<LogoutService> { LogoutServiceImpl(get()) }
}
