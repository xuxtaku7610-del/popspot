package com.popspot.app.presentation.mypage;

import com.popspot.app.domain.repository.ScrapRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class MyPageViewModel_Factory implements Factory<MyPageViewModel> {
  private final Provider<ScrapRepository> scrapRepositoryProvider;

  public MyPageViewModel_Factory(Provider<ScrapRepository> scrapRepositoryProvider) {
    this.scrapRepositoryProvider = scrapRepositoryProvider;
  }

  @Override
  public MyPageViewModel get() {
    return newInstance(scrapRepositoryProvider.get());
  }

  public static MyPageViewModel_Factory create(Provider<ScrapRepository> scrapRepositoryProvider) {
    return new MyPageViewModel_Factory(scrapRepositoryProvider);
  }

  public static MyPageViewModel newInstance(ScrapRepository scrapRepository) {
    return new MyPageViewModel(scrapRepository);
  }
}
