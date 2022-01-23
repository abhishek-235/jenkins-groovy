import pytest

@pytest.mark.check_class
def test_check_type():
    int_var = 5
    assert type(int_var) == int


@pytest.mark.cleanup
def test_cleanup():
    print("do cleanup stuff...")
    assert True