package Service;

import Model.SourceFile;
import View.SourceFileView;
import ViewModel.SourceFileViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;

public class MakeSourceFileViewTask extends Task<List<ViewTuple<SourceFileView, SourceFileViewModel>>> {
    List<SourceFile> files;

    public MakeSourceFileViewTask(List<SourceFile> files) {
        this.files = files;
    }

    @Override
    protected List<ViewTuple<SourceFileView, SourceFileViewModel>> call() throws Exception {
        List<ViewTuple<SourceFileView, SourceFileViewModel>> tuples = new ArrayList<>();
        files.forEach(f ->{
            ViewTuple<SourceFileView, SourceFileViewModel> viewTuple = FluentViewLoader.fxmlView(SourceFileView.class).load();
            viewTuple.getViewModel().initWithModel(f);
            tuples.add(viewTuple);
        });
        return tuples;
    }
}
