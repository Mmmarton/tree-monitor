package training.nuttyyokel.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import training.nuttyyokel.model.Tree;
import training.nuttyyokel.repository.TreeRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TreeServiceTest {


    private static final int TREE_ID = 1;
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

    @Test
    public void testSave_callsRepository() throws Exception {
        treeService.save(new Tree());
        verify(treeRepository, times(1)).save(any(Tree.class));
    }

    @Test
    public void testDelete_callsRepository() throws Exception {
        treeService.delete(TREE_ID);
        verify(treeRepository, times(1)).delete(anyInt());
    }
}