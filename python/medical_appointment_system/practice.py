
if __name__ == "__main__":

    for count in range(1,11):
        print('*' * count,  '*' * (11 - count), ' ' * (11 - count) + '*' *  count, ' ' * count + '*' *  (11 - count))