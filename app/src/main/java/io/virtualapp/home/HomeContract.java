package io.virtualapp.home;


import java.util.List;

import io.virtualapp.abs.BasePresenter;
import io.virtualapp.abs.BaseView;
import io.virtualapp.home.models.AppData;
import io.virtualapp.home.models.AppInfoLite;

/**
 * @author LodyChen
 */
/* package */ class HomeContract {

	/* package */ interface HomeView extends BaseView<HomePresenter> {

		void showPermissionDialog();

        void showBottomAction();

        void hideBottomAction();

		void showLoading();

		void hideLoading();

		void loadFinish(List<AppData> appModels);

		void loadError(Throwable err);

		void showGuide();

		void addAppToLauncher(AppData model);

        void removeAppToLauncher(AppData model);

		void refreshLauncherItem(AppData model);

		void askInstallGms();
	}

	/* package */ interface HomePresenter extends BasePresenter {

		boolean check64bitEnginePermission();

		void launchApp(AppData data);

		void dataChanged();

		void addApp(AppInfoLite info);

		void deleteApp(AppData data);

        void enterAppSetting(AppData data);

        String getLabel(String packageName);

        int getAppCount();
    }

}
