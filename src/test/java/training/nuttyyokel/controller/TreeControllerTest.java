package training.nuttyyokel.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import training.nuttyyokel.IntegrationTestParent;
import training.nuttyyokel.builders.TreeBuilder;
import training.nuttyyokel.builders.TreeRequestResponseBuilder;
import training.nuttyyokel.builders.TreeUpdateRequestBuilder;
import training.nuttyyokel.dto.FieldErrorResponse;
import training.nuttyyokel.dto.GenericResponse;
import training.nuttyyokel.dto.tree.TreeRequestResponse;
import training.nuttyyokel.dto.tree.TreeSaveResponse;
import training.nuttyyokel.dto.tree.TreeUpdateRequest;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;

public class TreeControllerTest extends IntegrationTestParent {

  private static final int MAX_HEALTH = 9;
  private static final int MAX_HEIGHT = 100000;

  private static final String MESSAGE_TOO_SMALL = "must be greater than or equal to 1";
  private static final String MESSAGE_HEALTH_TOO_BIG = "must be less than or equal to " + MAX_HEALTH;
  private static final String MESSAGE_HEIGHT_TOO_BIG = "must be less than or equal to " + MAX_HEIGHT;
  private static final String MESSAGE_MAY_NOT_BE_NULL = "may not be null";

  private static final String FIELD_HEIGHT = "height";
  private static final String FIELD_HEALTH = "health";
  private static final String FIELD_NAME = "name";
  private static final String FIELD_TYPE = "type";
  private static final String FIELD_DATE_PLANTED = "datePlanted";
  private static final String NAME_OTHER = "Jake";

  @Test
  public void testGetAll_whenEmpty_resultsEmptyList() {
    ResponseEntity<Object[]> result = getRestTemplate()
        .getForEntity(getWebPath().base().tree().build(), Object[].class);

    Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    Assert.assertEquals(0, result.getBody().length);
  }

  @Test
  @Transactional
  public void testGetAll_whenFilled_resultsPopulatedList() {
    createTree();
    createTree(NAME_OTHER);

    ResponseEntity<Object[]> result = getRestTemplate()
        .getForEntity(getWebPath().base().tree().build(), Object[].class);

    Assert.assertThat(result.getStatusCode(), is(HttpStatus.OK));
    Assert.assertEquals(2, result.getBody().length);

  }

  @Test
  public void testGetTree_whenEmpty_resultsEmptyResponse() {
    String path = getWebPath().base().tree(0).build();
    ResponseEntity<GenericResponse> result = getRestTemplate().getForEntity(path, GenericResponse.class);

    Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    Assert.assertEquals(TreeController.MESSAGE_INVALID_ID, result.getBody().getMessage());
  }

  @Test
  @Transactional
  public void testGetTree_whenExists_resultsRightTree() {
    int id = ((TreeSaveResponse) createTree().getBody()).getId();
    String path = getWebPath().base().tree(id).build();
    ResponseEntity<TreeRequestResponse> result = getRestTemplate().getForEntity(path, TreeRequestResponse.class);

    DateFormat format = DateFormat.getDateInstance();

    Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    Assert.assertEquals(TreeBuilder.NAME, result.getBody().getName());
    Assert.assertEquals(TreeBuilder.TYPE, result.getBody().getType());
    Assert.assertEquals(format.format(TreeBuilder.DATE_PLANTED), format.format(result.getBody().getDatePlanted()));
    Assert.assertEquals(TreeBuilder.HEALTH, result.getBody().getHealth());
    Assert.assertEquals(TreeBuilder.HEIGHT, result.getBody().getHeight(), 0);
  }

  @Test
  @Transactional
  public void testSave_whenValid_resultsSuccess() {
    ResponseEntity result = createTree();

    Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    Assert.assertThat(((TreeSaveResponse) result.getBody()).getId(), greaterThanOrEqualTo(0));
  }

  @Test
  @Transactional
  public void testSave_whenDuplicateName_resultsError() {
    createTree();
    ResponseEntity result = createTree();

    Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    Assert.assertEquals(TreeController.MESSAGE_INVALID_FIELD, ((GenericResponse) result.getBody()).getMessage());
    Assert.assertTrue(((FieldErrorResponse) result.getBody()).getField().contains(FIELD_NAME));
  }

  @Test
  @Transactional
  public void testSave_whenEmptyFields_resultsError() {
    TreeRequestResponse treeRequestResponse = new TreeRequestResponseBuilder()
        .emptyTreeRequestResponseBuilder()
        .build();
    ResponseEntity<FieldErrorResponse[]> result = getRestTemplate()
        .postForEntity(getWebPath().base().tree().build(), treeRequestResponse, FieldErrorResponse[].class);

    Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    Assert.assertThat(Arrays.asList(result.getBody()), containsInAnyOrder(
        new FieldErrorResponse(MESSAGE_TOO_SMALL, FIELD_HEIGHT),
        new FieldErrorResponse(MESSAGE_TOO_SMALL, FIELD_HEALTH),
        new FieldErrorResponse(MESSAGE_MAY_NOT_BE_NULL, FIELD_DATE_PLANTED),
        new FieldErrorResponse(MESSAGE_MAY_NOT_BE_NULL, FIELD_NAME),
        new FieldErrorResponse(MESSAGE_MAY_NOT_BE_NULL, FIELD_TYPE)
    ));
  }

  @Test
  @Transactional
  public void testSave_whenTooBigFields_resultsError() {
    TreeRequestResponse treeRequestResponse = new TreeRequestResponseBuilder()
        .normalTreeRequestResponseBuilder()
        .setHealth(MAX_HEALTH + 1)
        .setHeight(MAX_HEIGHT + 1)
        .build();
    ResponseEntity<FieldErrorResponse[]> result = getRestTemplate()
        .postForEntity(getWebPath().base().tree().build(), treeRequestResponse, FieldErrorResponse[].class);

    Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    Assert.assertThat(Arrays.asList(result.getBody()), containsInAnyOrder(
        new FieldErrorResponse(MESSAGE_HEIGHT_TOO_BIG, FIELD_HEIGHT),
        new FieldErrorResponse(MESSAGE_HEALTH_TOO_BIG, FIELD_HEALTH)
    ));
  }

  @Test
  @Transactional
  public void testUpdate_whenValid_resultsSuccess() {
    int id = ((TreeSaveResponse) createTree().getBody()).getId();

    TreeUpdateRequest updateRequest = new TreeUpdateRequestBuilder().normalTreeUpdateRequest().build();
    ResponseEntity<GenericResponse> result = getRestTemplate()
        .postForEntity(getWebPath().base().tree(id).build(), updateRequest, GenericResponse.class);

    Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    Assert.assertThat(result.getBody().getStatus(), is(HttpStatus.OK));
    Assert.assertThat(result.getBody().getMessage(), is(TreeController.MESSAGE_SUCCESS));
  }

  @Test
  @Transactional
  public void testUpdate_whenInvalidId_resultsErrorMessage() {
    int id = ((TreeSaveResponse) createTree().getBody()).getId();

    TreeUpdateRequest updateRequest = new TreeUpdateRequestBuilder().normalTreeUpdateRequest().build();
    ResponseEntity<GenericResponse> result = getRestTemplate()
        .postForEntity(getWebPath().base().tree(id + 1).build(), updateRequest, GenericResponse.class);

    Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    Assert.assertThat(result.getBody().getStatus(), is(HttpStatus.BAD_REQUEST));
    Assert.assertThat(result.getBody().getMessage(), is(TreeController.MESSAGE_INVALID_ID));
  }

  @Test
  @Transactional
  public void testUpdate_whenValuesTooSmall_resultsFieldErrorList() {
    int id = ((TreeSaveResponse) createTree().getBody()).getId();

    TreeUpdateRequest updateRequest = new TreeUpdateRequestBuilder().emptyTreeUpdateRequest().build();
    ResponseEntity<FieldErrorResponse[]> result = getRestTemplate()
        .postForEntity(getWebPath().base().tree(id).build(), updateRequest, FieldErrorResponse[].class);

    Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    Assert.assertEquals(2, result.getBody().length);
    Assert.assertThat(Arrays.asList(result.getBody()), containsInAnyOrder(
        new FieldErrorResponse(MESSAGE_TOO_SMALL, FIELD_HEIGHT),
        new FieldErrorResponse(MESSAGE_TOO_SMALL, FIELD_HEALTH)
    ));
  }

  @Test
  @Transactional
  public void testUpdate_whenValuesTooBig_resultsFieldErrorList() {
    int id = ((TreeSaveResponse) createTree().getBody()).getId();
    TreeUpdateRequest updateRequest = new TreeUpdateRequestBuilder()
        .emptyTreeUpdateRequest()
        .setHealth(MAX_HEALTH + 1)
        .setHeight(MAX_HEIGHT + 1)
        .build();

    ResponseEntity<FieldErrorResponse[]> result = getRestTemplate()
        .postForEntity(getWebPath().base().tree(id).build(), updateRequest, FieldErrorResponse[].class);

    Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    Assert.assertEquals(2, result.getBody().length);
    Assert.assertThat(Arrays.asList(result.getBody()), containsInAnyOrder(
        new FieldErrorResponse(MESSAGE_HEIGHT_TOO_BIG, FIELD_HEIGHT),
        new FieldErrorResponse(MESSAGE_HEALTH_TOO_BIG, FIELD_HEALTH)
    ));
  }

  @Test
  @Transactional
  public void testDelete_whenValid_resultsSuccess() {
    int id = ((TreeSaveResponse) createTree().getBody()).getId();
    String path = getWebPath().base().tree(id).build();

    ResponseEntity<GenericResponse> result = getRestTemplate().exchange(path, HttpMethod.DELETE, null, GenericResponse.class);

    Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    Assert.assertThat(result.getBody().getStatus(), is(HttpStatus.OK));
    Assert.assertThat(result.getBody().getMessage(), is(TreeController.MESSAGE_SUCCESS));
  }

  @Test
  @Transactional
  public void testDelete_whenInvalidId_resultsError() {
    String path = getWebPath().base().tree(0).build();

    ResponseEntity<GenericResponse> result = getRestTemplate().exchange(path, HttpMethod.DELETE, null, GenericResponse.class);

    Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    Assert.assertEquals(TreeController.MESSAGE_INVALID_ID, result.getBody().getMessage());
  }

  private ResponseEntity<? extends GenericResponse> createTree(String... name) {
    String finalName = (name == null || name.length < 1) ? TreeBuilder.NAME : name[0];
    String path = getWebPath().base().tree().build();
    TreeRequestResponse treeRequestResponse = new TreeRequestResponseBuilder()
        .normalTreeRequestResponseBuilder()
        .setName(finalName)
        .build();
    ResponseEntity<? extends GenericResponse> result = getRestTemplate()
        .postForEntity(path, treeRequestResponse, TreeSaveResponse.class);
    if (result.getStatusCode() != HttpStatus.OK) {
      result = getRestTemplate().postForEntity(path, treeRequestResponse, FieldErrorResponse.class);
    }
    return result;
  }

}