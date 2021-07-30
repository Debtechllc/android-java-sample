package debtechllc.deb.sonderblu.view_model;
import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
public class SonderBluViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    public SonderBluViewModelFactory(Application application) {
        mApplication = application;
    }
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new SonderBluViewModel(mApplication);
    }
}
