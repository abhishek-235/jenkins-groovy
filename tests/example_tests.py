import pytest

@pytest.mark.check_class
def test_check_type():
    assert True

@pytest.mark.check_class
def test_check_type2():
    assert True

@pytest.mark.e2e_test
def test_scenario1():
    assert True

@pytest.mark.e2e_test
def test_scenario2():
    assert True

@pytest.mark.cleanup
def test_cleanup():
    print("do cleanup stuff...")
    assert True