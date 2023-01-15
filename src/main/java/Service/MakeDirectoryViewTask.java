package Service;

import Model.Directory;
import View.DirectoryView;
import ViewModel.DirectoryViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.concurrent.Task;

public class MakeDirectoryViewTask extends Task<ViewTuple<DirectoryView, DirectoryViewModel>> {
    Directory model;

    public MakeDirectoryViewTask(Directory model) {
        this.model = model;
    }

    @Override
    protected ViewTuple<DirectoryView, DirectoryViewModel> call() throws Exception {
        updateMessage("Making buttons");
        updateProgress(-1,1);
        updateTitle("");
        ViewTuple<DirectoryView, DirectoryViewModel> viewTuple = FluentViewLoader.fxmlView(DirectoryView.class).load();
        viewTuple.getViewModel().initWithModel(model);
        updateMessage("Done!!");
        updateProgress(1,1);
        updateTitle("");
        return viewTuple;
    }
}
