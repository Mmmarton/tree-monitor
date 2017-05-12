package training.nuttyyokel.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;
import training.nuttyyokel.model.Tree;
import training.nuttyyokel.repository.TreeRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TreeServiceTest {

    @Mock
    private TreeRepository treeRepository;

    @InjectMocks
    private TreeService treeService;

    @Test
    public void testGetAll_whenListNotEmpty_returnsTreeList() throws Exception {
        Tree tree1 = new Tree();
        Tree tree2 = new Tree();
        List<Tree> list = Arrays.asList(tree1, tree2);
        when(treeRepository.findAll()).thenReturn(list);

        List<Tree> result = treeService.getAll();

        assertNotNull(result);
        assertEquals(list.size(), result.size());
        assertEquals(result.get(0), list.get(0));
        assertEquals(result.get(1), list.get(1));
    }

    @Test
    public void testGetAll_whenListEmpty_returnsNull() throws Exception {
        when(treeRepository.findAll()).thenReturn(null);

        assertNull(treeService.getAll());
    }
}