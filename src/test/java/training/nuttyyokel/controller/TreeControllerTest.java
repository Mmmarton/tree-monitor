package training.nuttyyokel.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import training.nuttyyokel.model.Tree;
import training.nuttyyokel.service.TreeService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TreeControllerTest {

    private static final String RESPONSE_SUCCESS = "success";
    private static final int TREE_ID = 1;

    @Mock
    private TreeService treeService;

    @InjectMocks
    private TreeController treeController;

    @Test
    public void testGetAll_whenListEmpty_returnsNull() throws Exception {
        when(treeService.getAll()).thenReturn(null);

        assertNull(treeController.getAll());
    }

    @Test
    public void testGetAll_whenListNotEmpty_returnsTreeList() throws Exception {
        Tree tree1 = new Tree();
        Tree tree2 = new Tree();
        List<Tree> list = Arrays.asList(tree1, tree2);
        when(treeService.getAll()).thenReturn(list);

        List<Tree> result = treeController.getAll();

        assertNotNull(result);
        assertEquals(list.size(), result.size());
        assertEquals(result.get(0), list.get(0));
        assertEquals(result.get(1), list.get(1));
    }

    @Test
    public void testSave_whenCorrectData_returnsSuccess() throws Exception {
        Tree tree = new Tree();

        ResponseEntity<String> response = treeController.save(tree);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(RESPONSE_SUCCESS, response.getBody());
    }

    @Test
    public void testDelete_whenCorrectId_returnsSuccess() throws Exception {
        Tree tree = new Tree();

        ResponseEntity<String> response = treeController.delete(TREE_ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(RESPONSE_SUCCESS, response.getBody());
    }
}